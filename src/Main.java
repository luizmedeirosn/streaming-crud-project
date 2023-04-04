import Servicos.Fachada;

public class Main {
    public static void main(String[] args) throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        Fachada f = Fachada.getFachada();
        f.startConnection();

        System.out.println();
        System.out.println(f.getProducao(33));

        // f.removerProducao(33);
        // System.out.println( f.getProducao(33));
        // System.out.println();

        // f.setLastStateApp();

        // System.out.println(f.getProducao(33));
        // System.out.println();

        f.finishConnection();
    }
}














        // // // Atores e Diretores de Undisputed II
        // Pessoa a10 = new Ator(110, "Michael \"George Chambers\" Jai", 55, 33, EnumTempoDeCarreira.ANOS, "https://pt.wikipedia.org/wiki/Michael_Jai_White");
        // Pessoa a11 = new Ator(111, "Scott \"Yuri Boyka\" Adkins", 46, 21, EnumTempoDeCarreira.ANOS, "https://pt.wikipedia.org/wiki/Scott_Adkins");
        // Pessoa a12 = new Ator(112, "Eli Danker", 74, 43, EnumTempoDeCarreira.ANOS, "https://en.wikipedia.org/wiki/Eli_Danker");
        // Pessoa d6 = new Diretor(26, "Isaac Florentine", 64, 27, "https://en.wikipedia.org/wiki/Isaac_Florentine");
        // Producao undisputedII = new Filme(33, "Undisputed II", "https://pt.wikipedia.org/wiki/Undisputed_II:_Last_Man_Standing", "1h 38m", "Nu Image", "16/01/2007");
        // undisputedII.addPessoa(a10).addPessoa(a11).addPessoa(a12).addPessoa(d6);