import React from 'react';

import '../../assets/global.css';
import './Modal.css';
interface ModalProps{
    children: React.ReactNode
}

export const Modal:React.FC<ModalProps> = (props: ModalProps) => {
return (
    <div className='modal-overlay'>
        <div className='modal-container bg-color'>
            {props.children}
        </div>
    </div>
)
}

