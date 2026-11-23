
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Calculadora {

    public static void main(String[] args) throws IOException {

        HttpServer servidor = HttpServer.create(
                new InetSocketAddress(8080), 0);

        servidor.createContext("/calcular", Calculadora::calcular);

        servidor.start();

        System.out.println("Servidor iniciado en http://localhost:8080");
    }

    public static void calcular(HttpExchange intercambio) throws IOException {

        String respuesta;

        try {
            // Obtener los parámetros de la petición
            String consulta = intercambio.getRequestURI().getQuery();

            Map<String, String> parametros = new HashMap<>();

            if (consulta != null) {
                for (String parametro : consulta.split("&")) {
                    String[] partes = parametro.split("=", 2);

                    if (partes.length == 2) {
                        parametros.put(
                                partes[0],
                                URLDecoder.decode(partes[1], StandardCharsets.UTF_8)
                        );
                    }
                }
            }

            // Obtener los valores
            int num1 = Integer.parseInt(parametros.get("num1"));
            int num2 = Integer.parseInt(parametros.get("num2"));
            String operacion = parametros.get("operacion");

            // Validar rango
            if (num1 < -99999 || num1 > 99999 ||
                num2 < -99999 || num2 > 99999) {

                throw new IllegalArgumentException(
                        "Los números deben estar entre -99999 y 99999");
            }

            // Realizar operación
            long resultado;

            switch (operacion) {
                case "suma":
                    resultado = (long) num1 + num2;
                    break;

                case "resta":
                    resultado = (long) num1 - num2;
                    break;

                case "multiplicacion":
                    resultado = (long) num1 * num2;
                    break;

                case "potencia":
                    resultado = (long) Math.pow(num1, num2);
                    break;

                default:
                    throw new IllegalArgumentException("Operación no válida");
            }

            respuesta = "Resultado: " + resultado;

        } catch (NumberFormatException e) {
            respuesta = "Error: introduce números enteros válidos";

        } catch (IllegalArgumentException e) {
            respuesta = "Error: " + e.getMessage();
        }

        byte[] datos = respuesta.getBytes(StandardCharsets.UTF_8);

        intercambio.getResponseHeaders().set(
                "Access-Control-Allow-Origin", "*");

        intercambio.getResponseHeaders().set(
                "Content-Type", "text/plain; charset=UTF-8");

        intercambio.sendResponseHeaders(200, datos.length);

        try (OutputStream salida = intercambio.getResponseBody()) {
            salida.write(datos);
        }
    }
}
