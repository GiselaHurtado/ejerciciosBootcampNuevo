export interface FilmEditDTO {
    title: string;
    description: string;
    length: number;
    rating: string;
    releaseYear: number;
    rentalDuration: string; 
    rentalRate: number;
    replacementCost: number;
    languageId: number;
    languageVOId: number;
    actors: number[];
    categories: number[];
  }
  