import java.util.Scanner;

class SosJumentoNordestino {


    
    public static double calcularTaxaReducao(int populacaoInicial, int populacaoAtual) {
        return (1 - (double) populacaoAtual / populacaoInicial) * 100;
    }

    
    public static boolean estaEmRiscoCritico(double taxaReducao) {
        return taxaReducao >= 90.0;
    }

    
    public static int simularImpactoAbate(int populacaoAtual, int abatesAnuais, int anosSimulados) {
        int populacaoRestante = populacaoAtual - (abatesAnuais * anosSimulados);
        return Math.max(populacaoRestante, 0); // Evita valores negativos
    }

    
    public static void exibirDeclaracaoFinal(int populacaoRestante, int anos) {
        System.out.println("\n--- RESULTADO DA SIMULAÇÃO ---");
        System.out.printf("População restante após %d ano(s): %d jumentos.\n", anos, populacaoRestante);

        if (populacaoRestante <= 0) {
            System.out.println("ALERTA: A espécie pode entrar em EXTINÇÃO TOTAL!");
        } else {
            System.out.println("\nAinda há esperança! Iniciativas de conservação são vitais.");
        }

        System.out.println("\nPrograma de Monitoramento Finalizado.");
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        int populacaoInicial = 1400000;
        int populacaoAtual = 84000;
        int abatesAnuais = 50000;

        System.out.println("=== S.O.S. JUMENTO NORDESTINO - SISTEMA DE MONITORAMENTO ===");
        System.out.println("População inicial de referência: " + populacaoInicial);
        System.out.println("População atual estimada: " + populacaoAtual);
        System.out.println("Abates anuais (simulados): " + abatesAnuais);

       
        double taxaReducao = calcularTaxaReducao(populacaoInicial, populacaoAtual);
        System.out.printf("\n[Análise Inicial] Taxa de Redução Histórica: %.2f%%\n", taxaReducao);

        
        if (estaEmRiscoCritico(taxaReducao)) {
            System.out.println("[Status] Espécie em Risco CRÍTICO (Redução > 90%)!");
        } else {
            System.out.println("[Status] Espécie em risco moderado.");
        }

        
        System.out.print("\nQuantos anos futuros você deseja simular o impacto dos abates (Ex: 1, 3, 5)? ");
        int anosSimulados = 0;
        try {
            anosSimulados = scanner.nextInt();
            if (anosSimulados < 0) {
                System.out.println("Número inválido de anos. O valor deve ser positivo.");
                scanner.close();
                return;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Encerrando o programa.");
            scanner.close();
            return;
        }

        
        int populacaoRestante = simularImpactoAbate(populacaoAtual, abatesAnuais, anosSimulados);

       
        exibirDeclaracaoFinal(populacaoRestante, anosSimulados);

        scanner.close();
    }
}
