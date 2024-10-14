package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FraseAnalizador {

    public static void main(String[] args) {
        // Texto a analizar
        String texto = "Este es un ejemplo. Aquí hay otra frase. Esta es la tercera frase.";

        // Llamar a la función que hace el análisis
        analizarTexto(texto);
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
            Map<Integer, Integer> contadorPalabrasPorLongitud = new HashMap<>();

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

                    // Contar cuántas palabras hay de cada longitud
                    contadorPalabrasPorLongitud.put(longitudPalabra,
                            contadorPalabrasPorLongitud.getOrDefault(longitudPalabra, 0) + 1);
                }
            }

            // Mostrar resultados por frase
            System.out.println("En la frase " + fraseNumero + " hay " + totalPalabrasEnFrase + " palabras.");
            for (Map.Entry<Integer, Integer> entry : contadorPalabrasPorLongitud.entrySet()) {
                System.out.println(" - Palabras de " + entry.getKey() + " letras: " + entry.getValue());
            }

            fraseNumero++;
        }

        // Mostrar el total de letras en el texto
        System.out.println("Total de letras en el texto: " + totalLetras);
    }
}
