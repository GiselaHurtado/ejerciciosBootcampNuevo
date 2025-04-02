export interface Film {
    filmId: number;
    title: string;
    description?: string;
    length?: number;
    releaseYear?: number;
    rentalDuration: number;
    rentalRate: number;
    replacementCost: number;
    languageId: number;
    languageVOId?: number;
    rating?: string;
       actors?: number[];
    categories?: number[];
  }
  