import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatMenuModule } from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDividerModule } from '@angular/material/divider';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';

import { TaskComponent } from './task/task.component';
import { FilterByDonePipe } from './tasks-page/filter-by-done.pipe';
import { TaskFormComponent } from './task-form/task-form.component';
import { TasksPageComponent } from './tasks-page/tasks-page.component';
import { TaskContainerComponent } from './task-container/task-container.component';

@NgModule({
  declarations: [
    TaskComponent,
    TaskContainerComponent,
    FilterByDonePipe,
    TasksPageComponent,
    TaskFormComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    MatSnackBarModule,
    MatMenuModule,
    MatToolbarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    ReactiveFormsModule,
  ],
  exports: [TasksPageComponent]
})
export class TasksModule { }
