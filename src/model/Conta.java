package model;

public abstract class Conta {
    private Cliente titular;
    private double saldo;

    public Conta(Cliente titular) {
        this.titular = titular;
        this.saldo = 0.0;
    }

    public Cliente getTitular() { return titular; }
    public double getSaldo() { return saldo; }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public abstract void transferir(Conta destino, double valor);
}