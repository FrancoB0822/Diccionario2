package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FraseAnalizador {

    public static void main(String[] args) {
        // Crear un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, ingresa el texto que deseas analizar:");

        // Leer múltiples líneas de texto hasta que el usuario escriba "FIN"
        StringBuilder texto = new StringBuilder();
        String linea;
        while (!(linea = scanner.nextLine()).equalsIgnoreCase("FIN")) {
            texto.append(linea).append(" ");
        }

        // Llamar a la función que hace el análisis
        analizarTexto(texto.toString());
    }

    public static void analizarTexto(String texto) {
        // Expresión regular para encontrar frases que empiezan con mayúscula y terminan con un punto
        Pattern pattern = Pattern.compile("[A-Z][^.]*\\.");
        Matcher matcher = pattern.matcher(texto);

        int totalLetras = 0;  // Para contar todas las letras del texto
        int fraseNumero = 1;  // Para numerar las frases

        // Iterar sobre todas las frases encontradas
        while (matcher.find()) {
            String frase = matcher.group(); // Obtener la frase
            System.out.println("Frase " + fraseNumero + ": " + frase);

            // Dividir la frase en palabras
            String[] palabras = frase.split("\\s+"); // Separar por espacios en blanco
            Map<Integer, List<String>> palabrasPorLongitud = new HashMap<>();

            // Contar letras y palabras por longitud
            int totalPalabrasEnFrase = 0;
            for (String palabra : palabras) {
                // Limpiar la palabra de posibles signos de puntuación al final
                palabra = palabra.replaceAll("[^a-zA-Z]", "");
                int longitudPalabra = palabra.length();

                // Contar la longitud de cada palabra (si no es vacía)
                if (longitudPalabra > 0) {
                    totalPalabrasEnFrase++;
                    totalLetras += longitudPalabra;

                    // Añadir la palabra a la lista correspondiente a su longitud
                    palabrasPorLongitud.computeIfAbsent(longitudPalabra, k -> new ArrayList<>()).add(palabra);
                }
            }

            // Mostrar resultados por frase
            System.out.println("En la frase " + fraseNumero + " hay " + totalPalabrasEnFrase + " palabras.");

            // Mostrar la cantidad de palabras de cada longitud junto con la lista de palabras
            for (Map.Entry<Integer, List<String>> entry : palabrasPorLongitud.entrySet()) {
                int longitud = entry.getKey();
                List<String> listaPalabras = entry.getValue();
                System.out.println(" - Palabras de " + longitud + " letras: " + listaPalabras.size() + " " + listaPalabras);
            }

            System.out.println(); // Espacio entre frases
            fraseNumero++;
        }

        // Mostrar el total de letras en el texto
        System.out.println("Total de letras en el texto: " + totalLetras);
    }
}
