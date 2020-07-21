import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { TaskFormComponent } from './../task-form/task-form.component';
import { TaskService } from './../task/task.service';
import { Task } from './../task/task';

@Component({
  selector: 'app-tasks-page',
  templateUrl: './tasks-page.component.html',
  styleUrls: ['./tasks-page.component.css']
})
export class TasksPageComponent implements OnInit {

  tasks: Task[];
  email: string;

  constructor(
    private service: TaskService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.service.read().subscribe(tasks => this.tasks = tasks);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(TaskFormComponent, {
      width: '450px',
      data: {}
    });

    dialogRef.afterClosed().subscribe((resultTask: Task) => {
      if (!resultTask) return;
      this.service.create(resultTask).subscribe(() => { 
        this.service.showMessage('Task created successfully') 
        this.ngOnInit();
      });
    });
  }

  saveEmail(event: Event) {
    event.preventDefault();
    this.service.saveEmail(this.email).subscribe(() => { this.service.showMessage('Email registered successfully') });
  }

  changed(): void {
    this.ngOnInit();
  }

}
