import { ArrowBackRounded, ClearRounded } from "@mui/icons-material"

export const displayIcon = (step: number):React.ReactNode =>{
    
    switch(step){

        case 1:
            return<ClearRounded  sx={{fontsize:25}}/>
        case 2:
            return<ArrowBackRounded  sx={{fontsize:25}}/>
        case 3:
            return<ArrowBackRounded  sx={{fontsize:25}}/>
        case 4:
            return<></>;
        case 5:
            return<ArrowBackRounded  sx={{fontsize:25}}/>
        case 6:
            return<></>;
        default:
            return<></>;


    }
}
export const iconClass = (step: number):string =>{
    if (step == 4 || step == 6){
    return "reg-step-counter-btn-disabled";
    }
    return "reg-step-counter-btn"
}