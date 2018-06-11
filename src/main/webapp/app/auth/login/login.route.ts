/*
*
*    @ AH.GHORAB
*
*/
import { NgModule, ModuleWithProviders } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login.component';

const routes : Routes = [
            {
                path: '',
                component: LoginComponent
            }
        ]

export const LoginRoute : ModuleWithProviders = RouterModule.forChild(routes);
