package fractales;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Validar argumentos
        if (args.length != 1) {
            System.err.println("Uso: java fractales.Main <archivo_configuracion> Proyecto Fractales: Bienvenido");
            System.exit(1);
        }

        String configFile = args[0];

        try {
            // Leer configuración
            ConfigReader config = new ConfigReader(configFile);

            // Leer parámetros básicos
            int degree = config.getInt("degree");
            Complex[] polynomial = new Complex[degree + 1];
            for (int i = 0; i <= degree; i++) {
                String key = "coef" + i;
                String value = config.get(key);
                if (value == null) {
                    throw new IllegalArgumentException("Falta el coeficiente: " + key);
                }
                polynomial[i] = Complex.parse(value);
            }

            // Validar parámetro de salida
            String output = config.get("output");
            if (output == null) {
                throw new IllegalArgumentException("Falta la salida de imagen: output");
            }

            // Crear y generar el fractal
            Fractal fractal = new ImagenFractal(config);
            fractal.generarImagenFractal();

            System.out.println("Imagen generada exitosamente en: " + output);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo de configuración: " + e.getMessage());
            System.exit(2);
        } catch (IllegalArgumentException e) {
            System.err.println("Error en configuración: " + e.getMessage());
            System.exit(3);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            System.exit(4);
        }
    }
}
