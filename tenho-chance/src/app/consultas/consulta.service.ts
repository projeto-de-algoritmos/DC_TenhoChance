import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Curso } from '../models/curso';
import { Observable } from 'rxjs';
import { Candidato } from '../models/candidato';
import { of } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  apiUrl = environment.apiUrl;
  urlCurso = `${this.apiUrl}/cursos`; 
  urlCandidato = `${this.apiUrl}/candidatos`;
  constructor(private http: HttpClient) { }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
}

  obterCursos(): Observable<Curso[]> {
    return this.http.get<Curso[]>(this.urlCurso);
  }

  obterCandidatos(urlSistemaSelecao: string, cursoId: number, pageNumber: number): Observable<any> {
    const urlCompleta = `${this.urlCandidato}${urlSistemaSelecao}?cursoId=${cursoId}&pageNumber=${pageNumber}`;
    
    const headers = new HttpHeaders({
      'Accept-Language': 'pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7',
      'Connection': 'keep-alive',
      'Referer': 'http://localhost:8080/swagger-ui/index.html',
      'Sec-Fetch-Dest': 'empty',
      'Sec-Fetch-Mode': 'cors',
      'Sec-Fetch-Site': 'same-origin',
      'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
      'accept': '*/*',
      'sec-ch-ua': '"Not.A/Brand";v="8", "Chromium";v="114", "Google Chrome";v="114"',
      'sec-ch-ua-mobile': '?0',
      'sec-ch-ua-platform': '"Linux"'
    });
    
    return this.http.get<Candidato[]>(urlCompleta, { headers });
  }
  

}
