package filtro;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/calcular", "/api/imc/*"})
public class IMCFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("IMCFiltro inicializado");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();

        System.out.println("IMCFiltro - Solicitud: " + method + " " + uri);

        // Validar solo peticiones POST a /calcular
        if ("POST".equalsIgnoreCase(method) && uri.contains("/calcular")) {

            String estatura = request.getParameter("estatura");
            String peso = request.getParameter("peso");
            String nombre = request.getParameter("nombre");
            String edad = request.getParameter("edad");

            // Validar que los campos no estén vacíos
            if (nombre == null || nombre.trim().isEmpty()) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "El nombre es obligatorio");
                return;
            }

            if (estatura == null || estatura.trim().isEmpty()) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "La estatura es obligatoria");
                return;
            }

            if (peso == null || peso.trim().isEmpty()) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "El peso es obligatorio");
                return;
            }

            // Validar que estatura y peso sean números válidos
            try {
                double estaturaVal = Double.parseDouble(estatura);
                double pesoVal = Double.parseDouble(peso);
                int edadVal = Integer.parseInt(edad);

                if (estaturaVal <= 0 || estaturaVal > 3.0) {
                    httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Estatura inválida. Debe estar entre 0 y 3.0 metros");
                    return;
                }

                if (pesoVal <= 0 || pesoVal > 500) {
                    httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Peso inválido. Debe estar entre 0 y 500 kg");
                    return;
                }

                if (edadVal <= 0 || edadVal > 120) {
                    httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                            "Edad inválida. Debe estar entre 1 y 120 años");
                    return;
                }

            } catch (NumberFormatException e) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Los valores de estatura, peso y edad deben ser numéricos");
                return;
            }
        }

        // Si todo está bien, continuar con la cadena de filtros
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("IMCFiltro destruido");
    }
}