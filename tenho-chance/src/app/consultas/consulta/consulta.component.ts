import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { Curso } from 'src/app/models/curso';
import { ConsultaService } from '../consulta.service';
import { SistemaSelecao } from 'src/app/models/sistema-selecao';
import { Candidato } from 'src/app/models/candidato';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { startWith, tap,catchError,map,switchMap } from 'rxjs/operators';
import { Observable, merge,of as observableOf,Subject } from 'rxjs';
import { MatTable } from '@angular/material/table';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit ,AfterViewInit{

  @ViewChild(MatPaginator ,{ static: true }) paginator!: MatPaginator;
  @ViewChild(MatTable, { static: false }) table?: MatTable<Candidato>;

  candidatos: Candidato[] = [];
  cursoSelecionadoChange$ = new Subject();
  sistemaSelecionadoChange$ = new Subject();

  cursos: Curso[] = [];
  _cursoSelecionado = 95;
  sistemasSelecao: SistemaSelecao[] = [];
  _sistemaSelecionado = '/universal';
  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false

  displayedColumns: string[] = ['numeroInscricao', 'nome', 'notaFinal', 'aprovado'];



  get cursoSelecionado() {
    return this._cursoSelecionado;
  }

  set cursoSelecionado(value) {
    if (value == -1) {
      this._cursoSelecionado = 95;
    } else {
      this._cursoSelecionado = value;
    }
    this.onCursoSelecionadoChange(this._cursoSelecionado);

  }


  get sistemaSelecionado() {
    return this._sistemaSelecionado;
  }

  set sistemaSelecionado(value) {
    if (value == 'null') {
      this._sistemaSelecionado = '/universal';
    } else {
      this._sistemaSelecionado = value;
    }
    this.onSistemaSelecionadoChange(this._sistemaSelecionado);
  }

  constructor(private consultaService: ConsultaService) {
    

  }
  
  ngAfterViewInit(): void {
    // Chame loadCandidatosPage sempre que a página do paginador é alterada
    this.paginator.page
      .pipe(
        tap(() => this.loadCandidatosPage())
      )
      .subscribe();
  }

  trataString(str: string): string {
    return str.split(' ').map(palavra =>
      palavra.charAt(0).toUpperCase() + palavra.slice(1).toLowerCase()
    ).join(' ');
  }

  

  onCursoSelecionadoChange(cursoId: number): void {
    this.cursoSelecionadoChange$.next(cursoId);
  }
  
  onSistemaSelecionadoChange(sistemaUrl: string): void {
    this.sistemaSelecionadoChange$.next(sistemaUrl);
  }
  

  loadCandidatosPage(): void {
    merge(this.paginator.page, this.cursoSelecionadoChange$, this.sistemaSelecionadoChange$)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.consultaService.obterCandidatos(this.sistemaSelecionado, this._cursoSelecionado, this.paginator.pageIndex);
        }),
        map(data => {
          // Use 'data.content' em vez de 'data.candidatos'
          this.isLoadingResults = false;
          this.isRateLimitReached = false;
          this.resultsLength = data.totalElements;
          this.candidatos = data.content;  // Atribua diretamente à 'this.candidatos'
          return this.candidatos;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          this.isRateLimitReached = true;
          return observableOf([]);
        })
      ).subscribe(data => {
        if (this.table) {
          this.table.renderRows();
        }
      });
  }
  
  

  ngOnInit(): void {

    this.sistemasSelecao = [
      { id: 1, nome: 'Universal', url: '/universal' },
      { id: 2, nome: 'Cotas para Negros', url: '/negros' },
      { id: 3, nome: 'Escola pública- PPI-PCD', url: '/escola-publica-nao-baixa-renda-ppi-pcd' },
      { id: 4, nome: 'Escola pública- PPI', url: '/escola-publica-nao-baixa-renda-ppi' },
      { id: 4, nome: 'Escola pública- PPI', url: '/escola-publica-nao-baixa-renda-pcd' },
      { id: 5, nome: 'Escola pública', url: '/escola-publica-nao-baixa-renda' },
      { id: 6, nome: 'Escola pública-Baixa Renda-PPI-PCD', url: '/escola-publica-baixa-renda-ppi-pcd' },
      { id: 7, nome: 'Escola pública-Baixa Renda-PPI', url: '/escola-publica-baixa-renda-ppi' },
      { id: 8, nome: 'Escola pública-Baixa Renda-PCD', url: '/escola-publica-baixa-renda-pcd' },
      { id: 9, nome: 'Escola pública-Baixa Renda', url: '/escola-publica-baixa-renda' },
    ];

    this.consultaService.obterCursos().subscribe(
      (cursos: Curso[]) => {
        this.cursos = cursos;
      },
      (error) => {
        console.log('Erro ao carregar cursos:', error);
      }
    );


   this.loadCandidatosPage();
  }
}
