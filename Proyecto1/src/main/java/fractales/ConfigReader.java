package fractales;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    private final Map<String, String> config;

    // Constructor que carga el archivo al construir
    public ConfigReader(String filename) throws IOException {
        this.config = new HashMap<>();
        loadConfig(filename);
    }

    // Cargar configuraciones desde archivo
    private void loadConfig(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue; // Ignorar líneas vacías o comentarios

                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    throw new IOException("Formato inválido en línea " + lineNumber + ": " + line);
                }

                String key = parts[0].trim();
                String value = parts[1].trim();

                config.put(key, value);
            }
        }
    }

    // Obtener un valor como String
    public String get(String key) {
        return config.get(key);
    }

    // Obtener un valor como int
    public int getInt(String key) {
        return Integer.parseInt(getRequired(key));
    }

    // Obtener un valor como double
    public double getDouble(String key) {
        return Double.parseDouble(getRequired(key));
    }

    // Obtener un valor como boolean
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getRequired(key));
    }

    // Obtener valor requerido (lanza excepción si falta)
    private String getRequired(String key) {
        String value = config.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Falta clave en configuración: " + key);
        }
        return value;
    }

    // Verificar si existe una clave
    public boolean has(String key) {
        return config.containsKey(key);
    }

    // Para depuración: imprimir toda la configuración
    @Override
    public String toString() {
        return config.toString();
    }
}
