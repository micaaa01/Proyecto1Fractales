package fractales;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImagenFractal extends OpFractal {
    /**
     * Constructor de la clase ImagenFractal.
     * @param config objeto ConfigReader que contiene la configuración del fractal.
     */

    public ImagenFractal(ConfigReader config) {
        super (
            config.get("output"),                              // nombre
            config.getInt("degree"),                           // grado
            config.getDouble("threshold"),                     // umbral
            construirPolinomio(config),                        // coeficientes
            config.getDouble("xmin"), config.getDouble("xmax"),
            config.getDouble("ymin"), config.getDouble("ymax"),
            config.getInt("ancho"), config.getInt("alto"),
            config.getInt("maxIter")
        );
    }
    /*
     * Método para generar la imagen fractal.
     * Este método recorre cada píxel de la imagencalcula su valor en el plano complejo y determina
     * el número de iteraciones necesarias para converger.
     * Luego, asigna un color basado en el número de iteraciones.
     * Finalmente, guarda la imagen en un archivo PNG.
     * @throws Exception si ocurre un error al guardar la imagen.
     * 
     */

    @Override
    public void generarImagenFractal() {
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        for (int px = 0; px < ancho; px++) {
            for (int py = 0; py < alto; py++) {
                double x = xmin + px * (xmax - xmin) / (ancho - 1);
                double y = ymax - py * (ymax - ymin) / (alto - 1); // eje Y invertido

                int iteraciones = evaluarPunto(x, y);
                int color = getColor(iteraciones);
                imagen.setRGB(px, py, color);
            }
        }

        try {
            File outputfile = new File(nombre + ".png");
            ImageIO.write(imagen, "png", outputfile);
            System.out.println("Imagen generada: " + outputfile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
        }
    }

    /**
     * Mapea la cantidad de iteraciones a un color.
     * @param iteraciones número de iteraciones.
     * @return color en formato RGB.
     */
    private int getColor(int iteraciones) {
        if (iteraciones == maxIter) {
            return Color.BLACK.getRGB();
        } else {
            float hue = (float) iteraciones / maxIter;
            return Color.HSBtoRGB(hue, 1.0f, 1.0f);
        }
    }
    /*
     * Método para construir el polinomio a partir de la configuración.
     * Lee los coeficientes del polinomio desde el archivo de configuración.
     * @param config objeto ConfigReader que contiene la configuración.
     * @return un arreglo de números complejos que representan los coeficientes del polinomio.
     * @throws IllegalArgumentException si falta algún coeficiente en la configuración.
     */
    private static Complex[] construirPolinomio(ConfigReader config) {
        int grado = config.getInt("degree");
        Complex[] poly = new Complex[grado + 1];

        for (int i = 0; i <= grado; i++) {
            String key = "coef" + i;
            String raw = config.get(key);  // Debe tener formato: (real,imag)
            try {
                poly[i] = Complex.parse(raw);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error al parsear el coeficiente " + key + ": \"" + raw + "\"", e);
            }
        }

        return poly;
    }
    
}
