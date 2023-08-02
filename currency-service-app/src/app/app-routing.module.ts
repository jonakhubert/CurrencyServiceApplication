import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './component/form/form.component';
import { DisplayDataComponent } from './component/display-data/display-data.component';

const routes: Routes = [
  {path: '', component: FormComponent},
  {path: 'display-data', component: DisplayDataComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
