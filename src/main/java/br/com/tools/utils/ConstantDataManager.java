/**
 * @author RodrigoBorges.
 * @date 01/03/2020
 */
package br.com.tools.utils;

public final class ConstantDataManager
{
	public static final String BLANK = "";
	public static final String ZERO_STRING = "0";
	public static final String VIRGULA_STRING = ",";
	public static final String PONTO_VIRGULA = ";";
	public static final String SIMPLE_ASPAS = "'";
	public static final String ON = "on";
	public static final String STATUS_DESCRIPTION = "status";

	//Parameters gerais
	public static final String PARAMETER_LOGIN = "login";
	public static final String PARAMETER_SENHA = "senha";
	public static final String PARAMETER_CODIGO = "codigo";
	public static final String PARAMETER_INATIVO = "inativo";
	public static final String PARAMETER_OBSERVACAO = "observacao";
	public static final String PARAMETER_RELATORIO = "relatorio";
	
	//Objetos
	public static final String OBJETO_USUARIO_SESSAO = "usuario";
	public static final String OBJETO_LICAO_APRENDIDA = "licao";
	public static final String OBJETO_REFERENCIA = "referencia";
	public static final String OBJETO_SUB_REFERENCIA = "subReferencia";
	public static final String OBJETO_USUARIO = "usuario";
	public static final String OBJETO_PROXIMO = "proximo";

	public static final String OBJETO_LISTA_LICAO_APRENDIDA = "licoes";
	public static final String OBJETO_LISTA_USUARIOS = "usuarios";
	public static final String OBJETO_LISTA_REFERENCIAS = "referencias";
	public static final String OBJETO_LISTA_SUB_REFERENCIAS = "subReferencias";
	
	//Mensagens 
	public static final String MESSAGE = "message";
	//Erro
	public static final String MESSAGE_DADOS_OBRIGATORIOS_NAO_INFORMADO = "Dados obrigatarios nao informado!";
	public static final String MESSAGE_PROBLEMAS_SALVAR_ALTERAR = "Nu00E3o foi possivel Salvar os Dados!";
	public static final String MESSAGE_PROBLEMAS_INATIVAR = "Não foi possível Inativar!";
	public static final String MESSAGE_PROBLEMAS_LOGIN = "Problemas ao Logar com o Usuário Informado!";
	public static final String MESSAGE_PROBLEMAS_LOGIN_INATIVO = "Problemas ao Logar, Usuário informado esta inativo!";
	public static final String MESSAGE_PROCESSO_FINALIZADO_ERRO = "Processo finalizado com Erro";
	//Sucesso
	public static final String MESSAGE_DADOS_ALTERADOS_SUCESSO = "Dados Alterados Com Sucesso!";
	public static final String MESSAGE_INATIVAR_SUCESSO = "Inativado Com Sucesso!";
	public static final String MESSAGE_ATIVAR_SUCESSO = "Ativado Com Sucesso!";
	public static final String MESSAGE_USUARIO_LOGADO_SUCESSO = "Usuario Logado com Sucesso!";
	public static final String MESSAGE_PROCESSO_FINALIZADO = "Processo Finalizado com Sucesso.";
}