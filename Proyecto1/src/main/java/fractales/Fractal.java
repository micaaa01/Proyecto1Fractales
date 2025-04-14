package fractales;

public abstract class Fractal {
    protected String nombre;
    protected int degree;
    protected double threshold;
    protected Complex[] polynomial;
    protected double xmin;
    protected double xmax;
    protected double ymin;
    protected double ymax;
    protected int ancho;
    protected int alto;
    protected int maxIter;

    public Fractal(String nombre, int degree, double threshold, Complex[] polynomial,
                   double xmin, double xmax, double ymin, double ymax,
                   int ancho, int alto, int maxIter) {
        this.nombre = nombre;
        this.degree = degree;
        this.threshold = threshold;
        this.polynomial = polynomial;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.ancho = ancho;
        this.alto = alto;
        this.maxIter = maxIter;
    }

    // Getters comunes (puedes añadir más si los necesitas)
    public String getNombre() {
        return nombre;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Complex[] getPolynomial() {
        return polynomial;
    }

    public double getThreshold() {
        return threshold;
    }

    public int getMaxIter() {
        return maxIter;
    }

    public double getXmin() {
        return xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public double getYmax() {
        return ymax;
    }

    // Método abstracto para generar el fractal
    public abstract void generarImagenFractal();
}
