import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  //Inicializando o HttpClient
  private http = inject(HttpClient);

  //Variáveis para exibir mensagens na página
  mensagemSucesso = signal<string>('');
  mensagemErro = signal<string>('');

  //Variável para armazenar os dados dos produtos
  produtos = signal<any[]>([]);

  //Criando um JSON para capturar os campos do forulário
  //e este JSON será utilizado para enviar os dados
  //para a API.
  produto = new FormGroup({
    nome: new FormControl('', [Validators.required]), //campo
    preco: new FormControl('', [Validators.required]), //campo
    quantidade: new FormControl('', [Validators.required]), //campo
  });

  //Evento executado quando a página é carregada..
  ngOnInit() {
    this.consultarProdutos(); //executando a consulta de produtos.
  }

  //Função que será executada quando 
  //o botão SUBMIT do formulário for clicado
  cadastrarProduto() {
    //Fazendo a chamada para o ENDPOINT POST da API (cadastrar produto)
    this.http.post('http://localhost:8081/api/v1/produtos', this.produto.value)
      .subscribe({ //aguardando o retorno da API
        next: (resposta: any) => { //capturando a resposta de sucesso!

          //exibindo a mensagem obtida da API
          this.mensagemSucesso.set(resposta.mensagem);
          this.mensagemErro.set('');

          //limpando os campos do formulário
          this.produto.reset();

          //Executando uma nova consulta dos produtos
          this.consultarProdutos();
        },
        error: (e) => { //capturando a resposta de erro!
          //exibindo a mensagem obtida da API
          this.mensagemErro.set(e.error.mensagem);
          this.mensagemSucesso.set('');
        }
      });
  }

  //Função para consultar os produtos da API
  consultarProdutos() {
    //fazendo uma requisição HTTP GET para a api de produtos
    this.http.get('http://localhost:8081/api/v1/produtos')
      .subscribe({ //Aguardando o retorno da API
        next: (dados) => { //Capturando a resposta de foi sucesso
          //guardar os dados dos produtos
          this.produtos.set(dados as any[]);
        },
        error: (e) => { //Capturando a resposta se foi erro
          console.log(e.error);
        }
      });
  }

}
