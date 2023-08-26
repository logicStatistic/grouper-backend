import React from 'react';

import '../assets/global.css';
import { RegisterModal } from '../features/register/component/registermodal/RegisterModal';
import './Home.css';

export const Home:React.FC = () => {

return (

    <div className='home-container bg-color'>
    <RegisterModal/>
    </div>

)
}

