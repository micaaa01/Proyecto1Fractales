package fractales;

public class OpFractal extends Fractal {

    public OpFractal(String nombre, int degree, double threshold, Complex[] polynomial,
                     double xmin, double xmax, double ymin, double ymax,
                     int ancho, int alto, int maxIter) {
        super(nombre, degree, threshold, polynomial,
              xmin, xmax, ymin, ymax,
              ancho, alto, maxIter);
    }

    /**
     * Evalúa un punto en el plano complejo y devuelve el número de iteraciones
     * necesarias para determinar su convergencia.
     *
     * @param x Coordenada real del punto.
     * @param y Coordenada imaginaria del punto.
     * @return Número de iteraciones antes de converger o maxIter si no converge.
     */
    public int evaluarPunto(double x, double y) {
        Complex z = new Complex(x, y);
        int iter = 0;

        while (iter < maxIter) {
            Complex pz = evaluarPolinomio(z);
            Complex dpz = derivadaPolinomio(z);
            if (dpz.abs() == 0) break; // evitar división por cero

            Complex zNext = z.subtract(pz.div(dpz));
            if (z.subtract(zNext).abs() < threshold) break;

            z = zNext;
            iter++;
        }

        return iter;
    }

    /**
     * Evalúa el polinomio en un punto z.
     */
    private Complex evaluarPolinomio(Complex z) {
        Complex resultado = new Complex(0, 0);
        for (int i = 0; i <= degree; i++) {
            Complex termino = polynomial[i].multiply(z.pow(i));
            resultado = resultado.add(termino);
        }
        return resultado;
    }

    /**
     * Evalúa la derivada del polinomio en un punto z.
     */
    private Complex derivadaPolinomio(Complex z) {
        Complex resultado = new Complex(0, 0);
        for (int i = 1; i <= degree; i++) {
            Complex termino = polynomial[i].multiply(new Complex(i, 0)).multiply(z.pow(i - 1));
            resultado = resultado.add(termino);
        }
        return resultado;
    }

    @Override
    public void generarImagenFractal() {
        // No implementado aquí, ya que OpFractal solo hace cálculo matemático.
        throw new UnsupportedOperationException("Este método debe ser implementado por una subclase que genere imágenes.");
    }
}
