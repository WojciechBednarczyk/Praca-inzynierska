import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainPageComponent} from './main-page/main-page.component';
import {RegisterComponent} from './register/register.component';
import {PasswordResetComponent} from "./password-reset/password-reset.component";
import {AtlasComponent} from './atlas/atlas.component';
import {ExerciseDetailsComponent} from './exercise-details/exercise-details.component';

const routes: Routes = [
  {path: "", component: MainPageComponent},
  {path: "login", component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'password-reset', component: PasswordResetComponent},
  {path: 'atlas', component: AtlasComponent},
  {path: 'atlas/:id', component: ExerciseDetailsComponent},
  {path: "**", pathMatch: 'full', redirectTo: "", component: MainPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
