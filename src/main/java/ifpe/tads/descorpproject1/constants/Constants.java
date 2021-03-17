package ifpe.tads.descorpproject1.constants;

public class Constants {
  
  public static class Erros {
    
    public static class Address {
      public static final String STREET = "O nome da rua não deve ser vazio";
      public static final String DISTRICT = "O nome do bairro não deve ser vazio";
      public static final String NUMBER = "O numero da casa deve ser entre 1 e 9999";
      public static final String COMPLEMENT = "O complemento deve ter no máximo 30 caracteres";
      public static final String POSTAL_CODE = "Número de cep invalido, exemplo: XX.XXX-XXX";
      public static final String STATE = "A sigla de estado deve ser válida";
    }
  
    public static class Author {
      public static final String NAME = "O Nome do autor deve ser valido";
    }
  
    public static class Book {
      public static final String TITLE = "O nome de um livro não deve ser estar em branco";
      public static final String RELEASE_YEAR = "Não trabalhamos com datas acima do ano 2100";
      public static final String PUBLISHER = "Um livro deve conter um nome de editora valida";
      public static final String CONDITION = "Um livro deve conter um estado valido";
      public static final String PRICE = "Um livro tem o valor minimo de R$0,01";
      public static final String BR_ISBN = "ISBN invalido. O ISBN deve ser brasileiro e com o " +
          "prefixo GS1.";
    }
    
    public static class Library {
      public static final String NAME = "O nome deve ser valido";
      public static final String ADDRESS = "O endereço deve ser valido";
    }
  
    public static class User {
      public static final String NAME = "O nome do usuario deve ser valido";
      public static final String LEGAL_DOCUMENT = "O cpf informado está em um formato invalido";
      public static final String BIRTH_DATE = "Datas de nascimento devem ser apenas datas passadas";
      public static final String PAYMENT = "O Valor minimo de um salario é 1000.00";
      public static final String EMAIL = "E-mail invalido";
      public static final String PHONE = "Informe no minimo um telefone do usuario";
    }
    
    public static class Seller {
      public static final String AREA = "A Area de atuação no vendedor não deve estar vazia";
    }
    
  }
}
