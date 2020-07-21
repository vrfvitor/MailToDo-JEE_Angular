import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';

import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  baseUrl = "http://localhost:8080/tasks";

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, 'x', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: isError ? 'msg-error' : 'msg-success'
    });
  }

  create(task: Task): Observable<Task> {
    return this.http.post<Task>(this.baseUrl, task);
  }

  read(): Observable<Task[]> {
    return this.http.get<Task[]>(this.baseUrl);
  }

  readById(id: number): Observable<Task> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Task>(url);
  }

  update(product: Task): Observable<Task> {
    const url = `${this.baseUrl}/${product.id}`;
    return this.http.put<Task>(url, product);
  }

  delete(id: number): Observable<Task> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<Task>(url);
  }

  setDone(id: number, done: boolean): Observable<any> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put(url, done);
  }

  saveEmail(email: string): Observable<any> {
    const url = 'http://localhost:8080/email';
    return this.http.post(url, email);
  }

}
