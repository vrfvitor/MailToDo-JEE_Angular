import { Pipe, PipeTransform } from '@angular/core';
import { Task } from '../task/task';

@Pipe({
  name: 'filterByDone'
})
export class FilterByDonePipe implements PipeTransform {

  transform(tasks: Task[], isDone: boolean = true): Task[] {
    if(!tasks) return;
    return tasks.filter(task => task.done === isDone);
  }

}
