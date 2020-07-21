import { TaskService } from './../task/task.service';
import { Component, OnInit, Input, Output } from '@angular/core';
import { Task } from '../task/task';
import { EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TaskFormComponent } from '../task-form/task-form.component';

@Component({
  selector: 'app-task-container',
  templateUrl: './task-container.component.html',
  styleUrls: ['./task-container.component.css']
})
export class TaskContainerComponent {

  @Input() title: string;
  @Input() tasks: Task[];
  @Output() warnChange = new EventEmitter();

  constructor(
    private service: TaskService,
    public dialog: MatDialog
  ) { }

  delete(id: number) {
    this.service.delete(id).subscribe(() => { 
      this.service.showMessage('Task deleted successfully.')
      this.warnChange.emit('');
    });
  }

  update(id: number) {
    this.service.readById(id).subscribe(task => {
      const dialogRef = this.dialog.open(TaskFormComponent, {
        width: '450px',
        data: {
          id: task.id,
          title: task.title,
          description: task.description,
          priority: task.priority
        }
      });

      dialogRef.afterClosed().subscribe((resultTask: Task) => {
        if (!resultTask) return;
        console.log(resultTask);
        this.service.update(resultTask).subscribe(() => {
          this.service.showMessage('Task updated successfully')
          this.warnChange.emit('');
        });
      });
    });
  }

  setDone(id: number, done: boolean = true) {
    this.service.setDone(id, done).subscribe(() => {
      this.service.showMessage('Task set as ' + (done ? 'done!' : 'undone!'))
      this.warnChange.emit('');
    });
  }

}
