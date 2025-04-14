# Proyecto 1

## Descripción 
Implementaremos un programa que:

1. Procesará polinomios definidos en el plano complejo.
2. Iterará sobre cada punto del plano para evaluar su convergencia o divergencia.
3. Generará una imagen en formato PNG o JPG que3 represente el fractal resultante.
---

## **Características Principales**
1. **Procesamiento de Polinomios Complejos:**
   - El programa evalúa polinomios en el plano complejo.
   - Se realizan operaciones como suma, resta, multiplicación y potencia sobre números complejos.

2. **Iteración y Evaluación:**
   - Cada punto del plano complejo es evaluado para determinar si converge o diverge bajo iteraciones sucesivas de la función.
   - Se utiliza un criterio de convergencia basado en un número máximo de iteraciones y un umbral de escape.

3. **Generación de Imágenes:**
   - Los resultados de las iteraciones se representan visualmente en una imagen.
   - Los colores de los píxeles representan el número de iteraciones necesarias para que un punto diverja o si converge.

4. **Configuración Personalizable:**
   - Los parámetros del fractal (como el tamaño de la imagen, el rango del plano complejo, el número máximo de iteraciones, etc.) se definen en un archivo de configuración externo.

---

## **Estructura del Proyecto**
El proyecto está organizado en las siguientes clases principales:

### **1. `ConfigReader`**
- **Propósito:** Leer y procesar un archivo de configuración externo.
- **Funciones principales:**
  - `get(String key)`: Obtiene un valor como cadena.
  - `getInt(String key)`: Obtiene un valor como entero.
  - `getDouble(String key)`: Obtiene un valor como número decimal.
  - `getBoolean(String key)`: Obtiene un valor como booleano.
  - `getRequired(String key)`: Lanza una excepción si una clave requerida no está presente.
- **Importancia:** Permite personalizar el fractal sin modificar el código fuente.

### **2. `Complex`**
- **Propósito:** Representar y operar con números complejos.
- **Operaciones implementadas:**
  - **Suma:** `z1 + z2 = (a + c) + (b + d)i`
  - **Resta:** `z1 - z2 = (a - c) + (b - d)i`
  - **Multiplicación:** `z1 * z2 = (ac - bd) + (ad + bc)i`
  - **Potencia:** Calcula la potencia de un número complejo mediante multiplicaciones sucesivas.
- **Importancia:** Es la base matemática para evaluar los polinomios en el plano complejo.

### **3. `OpFractal`**
- **Propósito:** Implementar las operaciones necesarias para evaluar los puntos del plano complejo.
- **Funciones principales:**
  - Iterar sobre cada punto del plano.
  - Evaluar si un punto converge o diverge.
  - Aplicar el criterio de escape para determinar el comportamiento de cada punto.
- **Importancia:** Define la lógica matemática detrás de la generación del fractal.

### **4. `ImagenFractal`**
- **Propósito:** Generar y guardar la imagen del fractal.
- **Funciones principales:**
  - Crear una imagen en memoria con los resultados de las iteraciones.
  - Asignar colores a los píxeles en función del número de iteraciones.
  - Guardar la imagen en formato PNG o JPG.
- **Importancia:** Es la salida visual del programa.

### **5. `Main`**
- **Propósito:** Punto de entrada del programa.
- **Funciones principales:**
  - Leer la configuración desde un archivo.
  - Inicializar las clases necesarias (`OpFractal`, `ImagenFractal`, etc.).
  - Ejecutar el proceso de generación del fractal.
- **Importancia:** Coordina todas las partes del programa.

---

## **Cómo Funciona el Programa**
1. **Lectura de Configuración:**
   - El programa lee un archivo de configuración (por ejemplo, `config.txt`) que define parámetros como:
     - Tamaño de la imagen (ancho y alto).
     - Rango del plano complejo (mínimo y máximo en los ejes real e imaginario).
     - Número máximo de iteraciones.
     - Umbral de escape.

2. **Iteración sobre el Plano Complejo:**
   - Cada píxel de la imagen corresponde a un punto en el plano complejo.
   - Se evalúa el comportamiento del punto bajo iteraciones sucesivas de la función compleja.

3. **Criterio de Escape:**
   - Si el módulo del número complejo supera un umbral definido, se considera que el punto diverge.
   - Si no diverge después de un número máximo de iteraciones, se considera que converge.

4. **Asignación de Colores:**
   - Los puntos que divergen se colorean en función del número de iteraciones necesarias para divergir.
   - Los puntos que convergen se colorean de manera uniforme.

5. **Generación de la Imagen:**
   - Los resultados de las iteraciones se guardan en una imagen en formato PNG o JPG.

---

## **Cómo Compilar y Ejecutar el Proyecto**

### **Requisitos Previos**
- **Java Development Kit (JDK):** Asegúrate de tener instalado JDK 8 o superior.
- **Apache Maven:** Necesario para compilar y ejecutar el proyecto.

### **Pasos para Compilar**
1. Abre una terminal y navega al directorio raíz del proyecto.
2. Ejecuta el siguiente comando para compilar el proyecto:
   ```bash
   mvn clean compile
   ```

Una vez que se haya compialdo de froma correcta, en la terminal aparecerá algo de este tipo:
   ```bash
   Uso: java fractales.Main <archivo_configuracion>
   ```

### Pasos para Ejecutar
1. Asegúrate de que el archivo de configuración (config.txt) esté en el directorio raíz del proyecto.
2. Ejecuta el siguiente comando para generar el fractal:
```bash
   mvn exec:java -Dexec.mainClass="fractales.Main"
   mvn exec:java -Dexec.mainClass="fractales.Main" -Dexec.args="<nombredelarchivo>.txt"
   ```
   Este último comando es para cuando ya querramos pasar el nombre de un archi von las características
   del fractal que querramos crear. 

#### Archivo de Configuración (config.txt)
- Ejemplo de archivo de configuración:
```bash
    nombre=FractalCubo
    degree=3
    threshold=0.0001
    coef0=(1.0,0.0)
    coef1=(0.0,0.0)
    coef2=(0.0,0.0)
    coef3=(-1.0,0.0)
    xmin=-2.0
    xmax=2.0
    ymin=-2.0
    ymax=2.0
    ancho=800
    alto=800
    maxIter=300
    escala=1.0
    output = fractal.png
   ```

### Detalles Importantes
Por qué se utiliza el plano complejo:

- Los fractales como el conjunto de Mandelbrot o Julia se basan en iteraciones de funciones complejas.
- El plano complejo permite representar cada punto como un número con parte real e imaginaria.
- Por qué se usa un criterio de escape:

- Es necesario para determinar si un punto diverge, ya que no podemos iterar infinitamente.
- El umbral de escape y el número máximo de iteraciones son parámetros clave para controlar la precisión y el rendimiento.
- Por qué se utiliza un archivo de configuración:

- Permite personalizar los parámetros del fractal sin modificar el código fuente.
- Facilita la experimentación con diferentes configuraciones.

### Ejemplo de Salida
Después de ejecutar el programa, se generará una imagen (por ejemplo, fractal.png) que representa el fractal basado en los parámetros definidos en la rama principal del proyecto. Los colores de la imagen reflejan el comportamiento de los puntos en el plano complejo.

