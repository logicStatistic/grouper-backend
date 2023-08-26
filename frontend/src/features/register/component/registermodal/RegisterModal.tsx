import React from 'react';
import { Modal } from '../../../../components/modal/Modal';
import { RegistrationStepCounter } from '../registrationstepcounter/RegistrationStepCounter';
import './RegisterModal.css';

export const RegisterModal:React.FC = () => {
return (
    <div className='register-modal'>
    <Modal>
        <div className='register-container'>
        <RegistrationStepCounter step={1}/>
        </div>
    </Modal>
    </div>
)
}


