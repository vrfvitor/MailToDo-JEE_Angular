import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Task } from './../task/task';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit {

  taskForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<TaskFormComponent>,
    @Inject(MAT_DIALOG_DATA) public task: Task,
    public fBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.taskForm = this.fBuilder.group({
      title: [this.task.title, Validators.required],
      priority: [this.task.priority, Validators.required],
      description: [this.task.description, Validators.required]
    })
  }

  getTask(): Task {
    this.task.title = this.taskForm.get('title').value;  
    this.task.priority = this.taskForm.get('priority').value;  
    this.task.description = this.taskForm.get('description').value;
    return this.task;  
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
