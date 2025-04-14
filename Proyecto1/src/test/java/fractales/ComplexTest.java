package fractales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;
import java.io.IOException;
import Proyecto1.src.main.java.fractales.Complex;
import Proyecto1.src.main.java.fractales.ConfigReader;
import Proyecto1.src.main.java.fractales.ImagenFractal;
import Proyecto1.src.main.java.fractales.OpFractal;
import java.awt.Color;
import java.awt.Color;

public class ComplexTest {
    /*
     * Test de la clase Complex
     * Verifica la correcta creación de un número complejo y sus operaciones básicas.
     * Se utilizan aserciones para validar los resultados esperados.
     * Se asume que la clase Complex tiene métodos para sumar, multiplicar y calcular el módulo.
     * Se utilizan números complejos simples para facilitar la verificación manual.
     * Se espera que los resultados sean correctos y se comparan con los valores esperados.
     * Se utilizan números reales e imaginarios enteros para simplificar la verificación.
     */
    @Test
    public void testAddition() {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, 4);
        Complex result = a.add(b);
        assertEquals(4.0, result.getReal());
        assertEquals(6.0, result.getImag());
    }

    @Test
    public void testMultiplication() {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, 4);
        Complex result = a.multiply(b);
        assertEquals(-5.0, result.getReal());
        assertEquals(10.0, result.getImag());
    }

    @Test
    public void testModulus() {
        Complex a = new Complex(3, 4);
        assertEquals(5.0, a.modulus());
    }
    /*
     * Test para la clase ConfigReader
     */

    public class ConfigReaderTest {

        @Test
        public void testLeerYProcesarArchivo() throws Exception {
            String fileName = "test_config.txt";
            String content = """
                width=800;
                height=600;
                degree=2;
                polynomial=(1+0i)x^2+(-1+0i);
                min=-2.0-1.5i;
                max=2.0+1.5i;
                iterations=100;
                threshold=2.0;
                color=(0,0,255);
                nombre=fract1;
                """;

            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();

            ConfigReader reader = new ConfigReader(fileName);
            assertEquals(800, reader.getIntParametro("width"));
            assertEquals(600, reader.getIntParametro("height"));
            assertEquals("fract1", reader.getParametro("nombre"));
        }
    }
    /*
     * Test para Fractal e ImagenFractal
     */
    public class ImagenFractalTest {

        @Test
        public void testPixelToComplex() {
            ImagenFractal fractal = new ImagenFractal("test", 0, 100, 2.0, 2, "(1+0i)x^2+(-1+0i)", -2, 2, -1.5, 1.5, 800, 600, Color.BLUE);
            Complex c = fractal.pixelToComplex(400, 300, 800, 600);
            assertEquals(0.0, c.getReal(), 0.01);
            assertEquals(0.0, c.getImag(), 0.01);
        }

        @Test
        public void testEvaluaPolinomio() {
            ImagenFractal fractal = new ImagenFractal("test", 0, 100, 2.0, 2, "(1+0i)x^2+(-1+0i)", -2, 2, -1.5, 1.5, 800, 600, Color.BLUE);
            Complex z = new Complex(1, 0);
            Complex result = fractal.evaluaPolinomio(z);
            assertEquals(0.0, result.getReal(), 0.01);  // Porque x^2 - 1 con x = 1 => 0
        }
    }
    /*
     * Pruebas para OpFractal
     */

    public class OpFractalTest {

        @Test
        public void testCalcularColor() {
            OpFractal op = new OpFractal("op", 0, 100, 2.0, 2, "(1+0i)x^2+(-1+0i)", -2, 2, -2, 2, 800, 600, Color.RED);
            Color result = op.calcularColor(50);
            assertNotNull(result);
        }

        @Test
        public void testEvaluaPolinomio() {
            OpFractal op = new OpFractal("op", 0, 100, 2.0, 2, "(1+0i)x^2+(-1+0i)", -2, 2, -2, 2, 800, 600, Color.RED);
            Complex z = new Complex(2, 0);
            Complex resultado = op.evaluaPolinomio(z);
            assertEquals(3.0, resultado.getReal(), 0.01); // 2^2 - 1 = 3
        }
    }


}
