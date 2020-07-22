<h1 align="center">MailToDo :heavy_check_mark: :envelope: </h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=JEE&message=8&color=informational&style=for-the-badge&logo=Java"/>
  <img src="https://img.shields.io/static/v1?label=Angular&message=9&color=red&style=for-the-badge&logo=Angular"/>
  <img src="http://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>
</p>

## Descrição do projeto :page_with_curl:
MailToDo é um gerenciador de tarefas simples, mas que conta com notificação via email. As tarefas devem conter um título, uma prioridade (alta, média ou baixa) e uma descrição. Além disso pode estar completa ou não. MailToDo disponibiliza um campo onde o usuário pode cadastrar seu email e de tempos em tempos, a aplicação verifica quais tarefas de alta prioridade ainda não foram completadas e então formula e envia um email personalizado com elas no endereço cadastrado.

## Layout da Aplicação :art:

<p align="center">
  <img  src="/media/email_sample.png" >
</p>

## Funcionalidades :gear:

:heavy_check_mark: CRUD de Tarefas

:heavy_check_mark: Notificação periódica via email

## Techs & Tools utilizadas :books:
Nesse projeto aproveitei para aplicar vários conhecimentos

- Java SE 8
  - Novas APIs Optional e Stream

- JEE 8
  - EJB: aproveitando as vantagens dos Session Beans e @Schedule
  - JAX-RS: implementação das endpoints, configuração do CORS para atender a qualquer request
  - JPA: integração facilitada com o banco de dados - ORM
  - JavaMail: implementação do serviço de envio de email
  - BeanValidation: validação dos dados (especificamente no DTO que recebe uma Task) 

- Persistência com MySQL

- WildFly 19 como Application Server

- Angular 9
  - Integração com [AngularMaterial](https://material.angular.io/) e [BootStrap](http://getbootstrap.com/)
  - Integração com REST API utilizando Services
  - Utilização de Modal para adição e alteração de tarefas
  - Utilização de ReactiveForms

## Desenvolvedor :computer:

<img src="https://avatars.githubusercontent.com/vrfvitor" width=115 align="left"/>
<h4>Vitor Rodrigues Ferreira</h4>

<h5>Conecte-se pelo <a href="https://www.linkedin.com/in/vrfvitor" target="_blank">LinkedIn</a></h4>

<h5>Siga pelo <a href="https://github.com/vrfvitor" target="_blank">GitHub</a>

## Licença :balance_scale:

The [MIT License](https://opensource.org/licenses/MIT)

Copyright :copyright: 2020 - MailToDo
