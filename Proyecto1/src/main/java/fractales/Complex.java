package fractales;

public class Complex {
    private final double re;
    private final double im;

    // Constructor
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // Parte real
    public double getRe() {
        return re;
    }

    // Parte imaginaria
    public double getIm() {
        return im;
    }

    // Módulo (magnitud)
    public double abs() {
        return Math.hypot(re, im); // √(re² + im²)
    }

    // Argumento (fase en radianes)
    public double arg() {
        return Math.atan2(im, re);
    }

    // Suma
    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    // Resta
    public Complex subtract(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    // Multiplicación
    public Complex multiply(Complex other) {
        double real = this.re * other.re - this.im * other.im;
        double imag = this.re * other.im + this.im * other.re;
        return new Complex(real, imag);
    }

    // División
    public Complex div(Complex other) {
        double denom = other.re * other.re + other.im * other.im;
        if (denom == 0) throw new ArithmeticException("División por cero");
        double real = (this.re * other.re + this.im * other.im) / denom;
        double imag = (this.im * other.re - this.re * other.im) / denom;
        return new Complex(real, imag);
    }

    // Conjugado
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    // Potencia entera (usando exponenciación por cuadrado)
    public Complex pow(int n) {
        Complex result = new Complex(1, 0);
        Complex base = this;

        if (n < 0) {
            base = new Complex(1, 0).div(base);
            n = -n;
        }

        while (n > 0) {
            if ((n & 1) == 1) result = result.multiply(base);
            base = base.multiply(base);
            n >>= 1;
        }

        return result;
    }

    // Raíz cuadrada (una de las dos)
    public Complex sqrt() {
        double modulus = Math.sqrt(this.abs());
        double angle = this.arg() / 2;
        return new Complex(modulus * Math.cos(angle), modulus * Math.sin(angle));
    }

    @Override
    public String toString() {
        return String.format("(%f %+f i)", re, im);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Complex)) return false;
        Complex other = (Complex) obj;
        return Double.compare(this.re, other.re) == 0 &&
               Double.compare(this.im, other.im) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(re) ^ Double.hashCode(im);
    }
    public static Complex parse(String s) {
        if (!s.startsWith("(") || !s.endsWith(")")) {
            throw new IllegalArgumentException("Formato inválido. Se esperaba (real,imag): " + s);
        }

        String contenido = s.substring(1, s.length() - 1);
        String[] partes = contenido.split(",");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Formato inválido. Se esperaban dos valores: " + s);
        }

        try {
            double real = Double.parseDouble(partes[0].trim());
            double imag = Double.parseDouble(partes[1].trim());
            return new Complex(real, imag);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("No se pudo convertir a double: " + s, e);
        }
    }
}
