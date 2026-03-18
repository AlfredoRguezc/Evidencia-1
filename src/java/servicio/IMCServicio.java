package servicio;

import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import modelo.CalculadoraIMC;
import org.springframework.stereotype.Service;

@Service
public class IMCServicio {

    // Lista en memoria que simula una base de datos
    private List<Usuario> registros = new ArrayList<>();

    // Crear registro
    public Usuario guardar(Usuario usuario) {
        registros.add(usuario);
        return usuario;
    }

    // Leer todos los registros
    public List<Usuario> obtenerTodos() {
        return registros;
    }

    // Leer un registro por índice
    public Usuario obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= registros.size()) {
            throw new IllegalArgumentException("Registro no encontrado");
        }
        return registros.get(indice);
    }

    // Actualizar registro por índice
    public Usuario actualizar(int indice, Usuario usuarioActualizado) {
        if (indice < 0 || indice >= registros.size()) {
            throw new IllegalArgumentException("Registro no encontrado");
        }
        registros.set(indice, usuarioActualizado);
        return usuarioActualizado;
    }

    // Eliminar registro por índice
    public void eliminar(int indice) {
        if (indice < 0 || indice >= registros.size()) {
            throw new IllegalArgumentException("Registro no encontrado");
        }
        registros.remove(indice);
    }

    // Calcular IMC de un usuario
    public double calcularIMC(Usuario usuario) {
        return CalculadoraIMC.calcularIMC(usuario);
    }

    // Obtener diagnóstico
    public String obtenerDiagnostico(double imc) {
        return CalculadoraIMC.obtenerDiagnostico(imc);
    }
}