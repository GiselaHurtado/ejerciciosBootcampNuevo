// src/app/models/page.model.ts
export interface Page<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    size: number;
    number: number; 
  }
  