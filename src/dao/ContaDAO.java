package dao;

import model.Conta;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Conta buscarConta(String cpf) {
        for (Conta conta : contas) {
            if (conta.getTitular().getCpf().equals(cpf)) {
                return conta;
            }
        }
        return null;
    }

    public List<Conta> listarContas() {
        return contas;
    }
}