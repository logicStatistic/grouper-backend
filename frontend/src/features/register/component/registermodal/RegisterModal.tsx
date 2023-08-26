import React, { useState } from 'react';
import { Modal } from '../../../../components/modal/Modal';
import { RegistrationStepCounter } from '../registrationstepcounter/RegistrationStepCounter';
import './RegisterModal.css';

export const RegisterModal:React.FC = () => {

    const [step, setStep]= useState<number>(4);
    const stepButtonClicked = () =>{
    step == 1||step ==4|| step >= 6 ? setStep(step): setStep(step-1);
    }

return (

    <Modal>
        <div className='register-container'>
        <RegistrationStepCounter step={step} changeStep={stepButtonClicked}/>
        </div>
    </Modal>

)
}


