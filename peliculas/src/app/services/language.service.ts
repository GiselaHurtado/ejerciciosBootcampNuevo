import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { LanguageDTO } from '../models/language.dto';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {
  // Verifica que la URL sea la correcta (por ejemplo, "language/v1")
  private baseUrl = 'http://localhost:8010/language/v1';

  constructor(private http: HttpClient) { }

  getLanguages(): Observable<LanguageDTO[]> {
    return this.http.get<any>(this.baseUrl).pipe(
      map(response => {
        const arr = Array.isArray(response) ? response : response.content;
        // Mapeamos cada objeto para que tenga languageId y name
        return arr.map((lang: any) => ({
          languageId: lang.languageId,
          name: lang.name
        }));
      })
    );
  }

  getLanguageById(id: number): Observable<LanguageDTO> {
    return this.http.get<any>(`${this.baseUrl}/${id}`).pipe(
      map(lang => ({ languageId: lang.languageId, name: lang.name }))
    );
  }
  
  createLanguage(language: LanguageDTO): Observable<any> {
    const payload = { name: language.name };
    return this.http.post(this.baseUrl, payload);
  }

  updateLanguage(language: LanguageDTO): Observable<any> {
    // Aseguramos que el payload y la URL usan "languageId"
    const payload = { languageId: language.languageId, name: language.name };
    return this.http.put(`${this.baseUrl}/${language.languageId}`, payload);
  }

  deleteLanguage(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
