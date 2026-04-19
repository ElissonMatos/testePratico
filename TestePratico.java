package com.mycompany.testepratico;
import static com.mycompany.testepratico.Funcionario.aplicarAumentoEmTodos;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;

public class TestePratico {
    //Classe Principal
    public static void main(String[] args) {
        
        Map<String, ArrayList<Funcionario>> mapa = new HashMap<>();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        //Inserindo dados dos funcionarios 
        System.out.println("Inserindo dados dos funcionarios...");
        
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000,10,18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990,5,12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961,5,2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988,10,14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995,1,5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",LocalDate.of(1999, 11, 19),new BigDecimal("1582.72"),"Operador"));
        funcionarios.add(new Funcionario("Arthur",LocalDate.of(1993, 3, 31),new BigDecimal("4071.84"),"Contador"));
        funcionarios.add(new Funcionario("Laura",LocalDate.of(1994, 7, 8),new BigDecimal("3017.45"),"Gerente"));
        funcionarios.add(new Funcionario("Heloísa",LocalDate.of(2003, 5, 24),new BigDecimal("1606.85"),"Eletricista"));
        funcionarios.add(new Funcionario("Helena",LocalDate.of(1996, 9, 2),new BigDecimal("2799.93"),"Gerente"));
        
        // Imprime a lista de funcionários
        System.out.println("\n\n\n\n\n\n");
        Funcionario.exibir(funcionarios);
        
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Removendo o funcionário João...");
        Funcionario.removerFuncionario(funcionarios, "João");//Removendo o funcionário João
        
         System.out.println("Lista atualizada:\n");
        Funcionario.exibir(funcionarios);
        System.out.println("\n\n\n\n\n\n");
        
        aplicarAumentoEmTodos(funcionarios);//Aplica um aumento de 10% em todos os salários
        System.out.println("Lista atualizada com aumento no salário de 10%: \n");
        Funcionario.exibir(funcionarios);
        System.out.println("\n\n\n\n\n\n");
        
        Map<String, List<Funcionario>> agrupado = funcionarios.stream().collect(java.util.stream.Collectors.groupingBy(f -> f.funcao));
        System.out.println("Lista agrupada por função: \n");
        Funcionario.exibirAgrupados(agrupado);//Exibi os nomes de funcionários agrupados por função
        System.out.println("\n\n\n\n\n\n");
        
        System.out.println("Lista de funcioários que fazem aniversário no mês 10 ou 12: \n");
        Funcionario.aniversariantesMes10e12(funcionarios);//Exibi os funcionário que fazem aniversário nos meses 10 ou 12
        System.out.println("\n\n\n\n\n\n");
        
        Funcionario.maisVelho(funcionarios);//Exibi o funcionário mais velho da lista
        System.out.println("\n\n\n\n\n\n");
        
        System.out.println("Lista de funcioários por ordem alfabetica: \n");
        Funcionario.ordenarPorNome(funcionarios);//Exibi os nomes dos funcionários por ordem alfabetica
        System.out.println("\n\n\n\n\n\n");
        
        Funcionario.totalSalarios(funcionarios);//Exibi a soma de todos os salários dos funcionários
        System.out.println("\n\n\n\n\n\n");
        
        System.out.println("Quantidade de salários minimos que cada funcionário recebe: \n");
        Funcionario.salariosMinimos(funcionarios);//Exibe a quantidade de sálarios minimos que cada funcionário recebe
    }
}

class Pessoa{
    public String nome;
    public LocalDate dataNascimento;

    // Construtor
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

class Funcionario extends Pessoa {
    private BigDecimal salario;
    String funcao;

    // Construtor
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    // Getters e Setters
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    //Imprime a lista de funcionários
    public static void exibir(List<Funcionario> lista) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("\n\nLISTA:\n\n");
        for (Funcionario f : lista){
           System.out.println("Nome: " + f.nome);
           System.out.println("Data de Nascimento: " + f.dataNascimento.format(formato));
           System.out.println("Salário: " + nf.format(f.salario));
           System.out.println("Função: " + f.funcao);
           System.out.println("------------------------------------");
        }
    }
    
    //Remove funcionário da lista por nome
    public static void removerFuncionario(ArrayList<Funcionario> lista, String nome) {
        lista.removeIf(f -> f.nome.equals(nome));
    }
    
    //Aplica aumento no salário
    public void aplicarAumento() {
       BigDecimal aumento = salario.multiply(new BigDecimal("0.10"));//Aumento de 10%
       salario = salario.add(aumento);
    }
    
    //Aplica aumento em todos os salários da lista
    public static void aplicarAumentoEmTodos(ArrayList<Funcionario> lista) {
       for (Funcionario f : lista) {
          f.aplicarAumento();
       }
    }
    
    //Agrupa os funcionários por função
    public static Map<String, ArrayList<Funcionario>> agruparPorFuncao(ArrayList<Funcionario> lista) {
       Map<String, ArrayList<Funcionario>> mapa = new HashMap<>();
       for (Funcionario f : lista) {
          // se não existir a função no mapa, cria a lista
          mapa.putIfAbsent(f.funcao, new ArrayList<>());
          // adiciona o funcionário na lista da função
          mapa.get(f.funcao).add(f);
       }
      return mapa;
    }
    
    //Exibi a lista de funcionários agrupados por função
    public static void exibirAgrupados(Map<String, List<Funcionario>> mapa) {
        for (String funcao : mapa.keySet()) {
           System.out.println("Função: " + funcao);
           for (Funcionario f : mapa.get(funcao)) {
              System.out.println(" - " + f.nome);
           }
           System.out.println("----------------------");
        }
    }
    
    //Exibi os funcionários que fazem aniversários nos meses 10 ou 12
    public static void aniversariantesMes10e12(List<Funcionario> lista) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Funcionario f : lista) {
           int mes = f.dataNascimento.getMonthValue();
           if (mes == 10 || mes == 12) {
              System.out.println("Nome: " + f.nome + " | Data: " + f.dataNascimento.format(formato));
            }
        }
    }
    
    //Imprime o funcionário mais velho da lista
    public static void maisVelho(List<Funcionario> lista) {
       if (lista.isEmpty()) {
         System.out.println("Lista vazia.");
         return;
       }
       Funcionario maisVelho = lista.get(0);
       for (Funcionario f : lista) {
          if (f.dataNascimento.isBefore(maisVelho.dataNascimento)) {
             maisVelho = f;
          }
        }
        int idade = Period.between(maisVelho.dataNascimento, LocalDate.now()).getYears();
        System.out.println("Funcionário mais velho:");
        System.out.println("Nome: " + maisVelho.nome);
        System.out.println("Idade: " + idade + " anos");
    }
    
    //Imprime a lista ordenada por nome
    public static void ordenarPorNome(List<Funcionario> lista) {
       Collections.sort(lista, Comparator.comparing(f -> f.nome));
       for (Funcionario f : lista) {
         System.out.println(f.nome);
       }
    }
    
    //Faz a soma de todos os salários e exibe o resultado
    public static void totalSalarios(List<Funcionario> lista) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario f : lista) {
           total = total.add(f.salario);
        }
        System.out.println("Total dos salários: " + nf.format(total));
    }
    
    //Calcula e exibe a quantidade de salários minimos que cada funcionário recebe
    public static void salariosMinimos(List<Funcionario> lista) {
       BigDecimal salarioMinimo = new BigDecimal("1212.00");
       for (Funcionario f : lista) {
          BigDecimal quantidade = f.salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
          System.out.println("Nome: " + f.nome + " | Salários mínimos: " + quantidade);
       }
    }
}