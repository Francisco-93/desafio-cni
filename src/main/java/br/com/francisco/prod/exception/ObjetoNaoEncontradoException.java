package br.com.francisco.prod.exception;

public class ObjetoNaoEncontradoException extends RuntimeException{

    public ObjetoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
