import { Component, Input } from '@angular/core';
import { Priorirty, Task } from './task';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent {

  @Input() task: Task;

  getPriority(id: number) {
    return Priorirty[id];
  }

  getColor(priorirty: Priorirty) {
    const id = Priorirty[priorirty];
    enum Color { green, yellow, red };
    return Color[id];
  }

}
