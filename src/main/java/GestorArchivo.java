import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorArchivo {

    //Crear un directorio
    public void crearDirectorio(String ruta) {
        Path directorio = Paths.get(ruta);
        if (Files.exists(directorio)) {
            System.out.println("el directorio ya existe");
        } else {
            try {
                Files.createDirectories(directorio);
                System.out.println("el directorio fue creado");
            } catch (IOException e) {
                System.out.println("el directorio no pudo ser creado");
            }
        }
    }

    //crear un archivo
    public void crearArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
            System.out.println("El archivo fue creado exitosamente");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser creado");
        }
    }

    //leer un archivo
    public String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
        return contenido;
    }

    //eliminar un archivo
    public void eliminarArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        try {
            Files.deleteIfExists(archivo);
            System.out.println("El archivo fue eliminado exitosamente");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser eliminado");
        }
    }

    //nuevaLinea
    public void nuevaLinea(String ruta, String contenido) {
        String oldContenido = leerArchivo(ruta);
        crearArchivo(ruta, oldContenido + "\n" + contenido);
    }

    public String[] listaArchivos(String ruta) {
        File f = new File(ruta);
        return f.list();
    }
}
