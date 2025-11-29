package cadastropoo.model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> lista;
    
    public PessoaJuridicaRepo() {
        this.lista = new ArrayList<>();
    }
    
    public void inserir(PessoaJuridica p) {
        lista.add(p);
    }
    
    public void alterar(PessoaJuridica p) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == p.getId()) {
                lista.set(i, p);
                break;
            }
        }
    }
    
    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }
    
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<PessoaJuridica> obterTodos() {
        return lista;
    }
    
    public void persistir(String nomeArquivo) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        }
    }
    
    public void recuperar(String nomeArquivo) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(nomeArquivo))) {
            lista = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}

