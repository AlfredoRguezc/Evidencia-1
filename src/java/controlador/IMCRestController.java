package controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servicio.IMCServicio;
import servicio.IMCServicio;

@RestController
@RequestMapping("/api/imc")
public class IMCRestController {

    @Autowired
    private IMCServicio imcServicio;

    // GET - Obtener todos los registros
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(imcServicio.obtenerTodos());
    }

    // GET - Obtener un registro por índice
    @GetMapping("/{indice}")
    public ResponseEntity<?> obtenerPorIndice(@PathVariable int indice) {
        try {
            Usuario usuario = imcServicio.obtenerPorIndice(indice);
            double imc = imcServicio.calcularIMC(usuario);
            String diagnostico = imcServicio.obtenerDiagnostico(imc);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("usuario", usuario);
            respuesta.put("imc", Math.round(imc * 100.0) / 100.0);
            respuesta.put("diagnostico", diagnostico);

            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Registro no encontrado en índice: " + indice);
        }
    }

    // POST - Crear nuevo registro
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        try {
            Usuario guardado = imcServicio.guardar(usuario);
            double imc = imcServicio.calcularIMC(guardado);
            String diagnostico = imcServicio.obtenerDiagnostico(imc);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("usuario", guardado);
            respuesta.put("imc", Math.round(imc * 100.0) / 100.0);
            respuesta.put("diagnostico", diagnostico);
            respuesta.put("mensaje", "Registro creado exitosamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT - Actualizar registro por índice
    @PutMapping("/{indice}")
    public ResponseEntity<?> actualizar(@PathVariable int indice, @RequestBody Usuario usuario) {
        try {
            Usuario actualizado = imcServicio.actualizar(indice, usuario);
            double imc = imcServicio.calcularIMC(actualizado);
            String diagnostico = imcServicio.obtenerDiagnostico(imc);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("usuario", actualizado);
            respuesta.put("imc", Math.round(imc * 100.0) / 100.0);
            respuesta.put("diagnostico", diagnostico);
            respuesta.put("mensaje", "Registro actualizado exitosamente");

            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE - Eliminar registro por índice
    @DeleteMapping("/{indice}")
    public ResponseEntity<?> eliminar(@PathVariable int indice) {
        try {
            imcServicio.eliminar(indice);
            return ResponseEntity.ok("Registro eliminado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}