package controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import modelo.Usuario;
import modelo.CalculadoraIMC;

@Controller
public class IMCController {

    @GetMapping("/")
    public String mostrarFormulario() {
        return "index";
    }

    @PostMapping("/calcular")
    public String calcularIMC(
            @RequestParam String nombre,
            @RequestParam int edad,
            @RequestParam String sexo,
            @RequestParam double estatura,
            @RequestParam double peso,
            Model model) {

        try {
            Usuario usuario = new Usuario(nombre, edad, sexo, estatura, peso);
            double imc = CalculadoraIMC.calcularIMC(usuario);
            String diagnostico = CalculadoraIMC.obtenerDiagnostico(imc);

            model.addAttribute("usuario", usuario);
            model.addAttribute("imc", imc);
            model.addAttribute("diagnostico", diagnostico);

            return "resultado";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
    }
}