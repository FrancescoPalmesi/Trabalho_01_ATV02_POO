package model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente titular) {
        super(titular);
    }

    @Override
    public void transferir(Conta destino, double valor) {
        if (sacar(valor)) {
            destino.depositar(valor);
        }
    }
}