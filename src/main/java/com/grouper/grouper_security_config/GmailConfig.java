package com.grouper.grouper_security_config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.grouper.grouper_exception_control.FileDoesNotExistException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class GmailConfig {

    private static final String APP_NAME = "Grouper";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String  TOKEN_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);
    private static final String CREDENTIAL_FILE = "/credential.json";
    private Credential getCredential(final NetHttpTransport HTTP_TRANSPORT) throws IOException{
        InputStream in = GmailConfig.class.getResourceAsStream(CREDENTIAL_FILE);

        if (in == null){
            throw new FileDoesNotExistException();
        }

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder
                        (HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKEN_DIRECTORY_PATH)))
                        .setAccessType("offline")
                        .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();

        return new AuthorizationCodeInstalledApp(flow, receiver)
                .authorize("users");
     }

     @Bean
     public Gmail getService(){
        NetHttpTransport HTTP_TRANSPORT ;

        try{
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

             return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY,getCredential(HTTP_TRANSPORT))
                     .setApplicationName(APP_NAME)
                     .build();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
     }
}

