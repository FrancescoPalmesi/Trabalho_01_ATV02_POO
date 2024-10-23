package model;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente titular) {
        super(titular);
    }

    @Override
    public void transferir(Conta destino, double valor) {
        if (sacar(valor)) {
            destino.depositar(valor);
        }
    }
}