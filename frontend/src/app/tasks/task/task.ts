export interface Task {
    id?: number;
    title: string;
    description: string;
    done: boolean;
    priority: Priorirty; 
}

export enum Priorirty {
    LOW, MEDIUM, HIGH
}
