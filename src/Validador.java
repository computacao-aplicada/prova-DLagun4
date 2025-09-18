public class Validador {

    // Método principal para validar CPF
    public static boolean validarCPF(String cpf) {
        // Verifica se o CPF é nulo ou vazio
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        // Remove pontos e traços do CPF
        String limpo = cpf.trim().replaceAll("[.\\-]", "");

        // Verifica se tem exatamente 11 dígitos numéricos
        if (!limpo.matches("\\d{11}")) {
            return false;
        }

        // Rejeita CPFs com todos os dígitos iguais (ex: 11111111111)
        if (limpo.chars().distinct().count() == 1) {
            return false;
        }

        // Chama método auxiliar que valida os dígitos verificadores
        return checarDigitos(limpo);
    }

    // Método auxiliar que calcula e confere os dois dígitos verificadores
    private static boolean checarDigitos(String cpf) {
        // Converte cada caractere em número inteiro e guarda em array
        int[] d = cpf.chars().map(c -> c - '0').toArray();

        // --------- Cálculo do primeiro dígito verificador (DV1) ---------
        int s1 = 0;
        for (int i = 0; i < 9; i++) s1 += d[i] * (10 - i); // soma ponderada
        int r1 = s1 % 11;
        int dv1 = (r1 < 2) ? 0 : 11 - r1; // regra do DV1

        // Se o primeiro DV não confere, CPF é inválido
        if (d[9] != dv1) return false;

        // --------- Cálculo do segundo dígito verificador (DV2) ---------
        int s2 = 0;
        for (int i = 0; i < 10; i++) s2 += d[i] * (11 - i); // soma ponderada
        int r2 = s2 % 11;
        int dv2 = (r2 < 2) ? 0 : 11 - r2; // regra do DV2

        // Retorna true se o DV2 for igual ao informado, senão false
        return d[10] == dv2;
    }
}
