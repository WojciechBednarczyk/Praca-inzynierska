import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainPageComponent} from './main-page/main-page.component';
import {RegisterComponent} from './register/register.component';
import {PasswordResetComponent} from "./password-reset/password-reset.component";
import {AtlasComponent} from './atlas/atlas.component';
import {ExerciseDetailsComponent} from './exercise-details/exercise-details.component';
import {MyProfileComponent} from './my-profile/my-profile.component';
import {ProfileRouteResolver} from './resolvers/profile-route-resolver';

const routes: Routes = [
  {path: "", component: MainPageComponent},
  {path: "login", component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'password-reset', component: PasswordResetComponent},
  {path: 'atlas', component: AtlasComponent},
  {path: 'atlas/:id', component: ExerciseDetailsComponent},
  {
    path: 'my-profile', component: MyProfileComponent, resolve: {
      data: ProfileRouteResolver
    }
  },
  {path: "**", pathMatch: 'full', redirectTo: "", component: MainPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [ProfileRouteResolver]
})
export class AppRoutingModule { }
