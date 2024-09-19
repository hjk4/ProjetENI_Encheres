import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AccountCreationComponent } from './components/account-creation/account-creation.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  {
    path: 'home',
    title: 'Accueil',
    component: HomeComponent
  },
  {
    path: 'account-creation',
    title: 'S\'inscrire',
    component: AccountCreationComponent
  },
  {
    path: 'login',
    title: 'Se connecter',
    component: LoginComponent
  }

];
