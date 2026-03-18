package modelo;

public class CalculadoraIMC {
    
    public static double calcularIMC(Usuario usuario) {
        if (usuario.getEstatura() <= 0) {
            throw new IllegalArgumentException("La estatura debe ser mayor a 0");
        }
        return usuario.getPeso() / (usuario.getEstatura() * usuario.getEstatura());
    }
    
    public static String obtenerDiagnostico(double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
}
