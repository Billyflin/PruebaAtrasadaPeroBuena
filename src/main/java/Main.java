import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String rutaPersonas = "Persona.txt";
    public static String rutaMascotas = "Mascota.CSV";
    public static String rutaVacunaciones = "Vacunaciones.txt";
    public static ArrayList<Mascotas> mascotas = new ArrayList<>();
    public static ArrayList<Personal> personal = new ArrayList<>();
    public static ArrayList<Voluntarios> voluntarios = new ArrayList<>();
    public static ArrayList<Mascotas> mascotaVacunada= new ArrayList<>();
    public static ArrayList<Mascotas> mascotaSana= new ArrayList<>();

    public static void main(String[] args) throws IOException, CsvException, ParseException {
        csvMascota();
        csvPersona();
        int x=-1;
        do{
            menu();
            System.out.println("desea realizar otra acción en el menu? (No (0) / Si (1))");
            x=Validar(1);
        }while (x!=0);
    }


    public static void menu() throws IOException, CsvException, ParseException {
        System.out.println("MENU");
        System.out.println("[1]Ingresar Nuevo Empleado o Voluntario");
        System.out.println("[2]Registrar Mascota");
        System.out.println("[3]Visualizar Mascota");
        System.out.println("[4]Vacunar Mascota");
        System.out.println("[5]Mascotas Vacunadas");
        System.out.println("[6]Ver personal");
        System.out.println("[0]SALIR");
        switch (Validar(6)) {
            case 0 -> System.out.println("Gracias!");
            case 1 -> ingresarEoV();
            case 2 -> registrarMascota();
            case 3 -> visualizarMascota();
            case 4 -> vacunar();
            case 5 -> historialVacunados();
            case 6 -> verPersonal();
        }
    }

    private static void verPersonal() {
        for (Personal p:personal) {
            p.mostrarPersona();
        }
    }

    private static void ingresarEoV() {
        Scanner sc = new Scanner(System.in);

        System.out.println("¿Es personal o voluntario?");
        System.out.println("1) Personal o 2) Voluntario");

        int a = Validar(2);
        if (a == 1) {
            System.out.println("Ingrese el Nombre");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el Apellido");
            String apellido = sc.nextLine();
            System.out.println("Ingrese el rut");
            String rut = convertir(sc.nextLine());
            System.out.println("Ingrese la Especialidad");
            String especialidad = sc.nextLine();
            Personal personnueva = new Personal(nombre, apellido, rut, especialidad);
            personal.add(personnueva);
            if (Files.exists(Path.of(rutaPersonas))) {
                personnueva.guardarEnArchivo(rutaPersonas);
            } else if (!Files.exists(Path.of(rutaPersonas))) {
                personnueva.guardarEnArchivo(rutaPersonas);

            }
        }
        for (Personal p:personal) {
            p.mostrarPersona();

        }
    }

    public static String convertir(String numero) {
        numero = numero.replaceAll("[^0-9]", "");
        if (numero.equals("")) {
            numero = "0";
        }

        return numero;
    }

    private static void historialVacunados() {

        try (CSVReader reader = new CSVReader(new FileReader(rutaVacunaciones))) {
            String[] nextLine = null;

            while ((nextLine = reader.readNext()) != null) {
                String nombre2=nextLine[0];
                String tipo2=nextLine[1];
                String sexo2=nextLine[2];
                String vacuna=nextLine[3];
                String fecha=nextLine[4];
                String efectividad=nextLine[5];
                mascotaVacunada.add(new Mascotas(nombre2, tipo2, sexo2, vacuna, fecha, efectividad));
            }
            for (Mascotas m:mascotaVacunada) {
                m.mostrarMascota();
            }

        } catch (Exception e) {
            System.out.println(e);

        }
    }


    private static void vacunar() {
        visualizarMascota();
        System.out.println("Ingrese el indice de la mascota:           " +"Mascotas en la lista=>"+mascotas.size());
        Mascotas index=mascotas.get(Validar(mascotas.size())-1);
        if(index.getTipo().equals("Gato")){
            System.out.println("Ingrese el tipo de vacuna");
            index.VacunarGato(Validar(2));
        }else if(index.getTipo().equals("Perro")){
            System.out.println("Ingrese el tipo de vacuna");
            index.VacunarGato(Validar(2));
        }
        index.mostrarMascota();
        index.guardarEnArchivo(rutaVacunaciones);
    }

    private static void visualizarMascota() {
        for (Mascotas m:mascotas) {
            m.mostrarMascota();
        }
    }

    public static void registrarMascota() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la mascota");
        String nombre = sc.nextLine();
        System.out.println("Ingrese si es gato o perro");
        String tipo = sc.nextLine();
        System.out.println("Escoga Sexo => 1)Macho 2)Hembra");
        int sexo = Validar(2);
        Mascotas nueva = new Mascotas(nombre, tipo, sexo);
        mascotas.add(nueva);
        if (Files.exists(Path.of(rutaMascotas))) {
            nueva.guardarEnArchivo(rutaMascotas);
        } else if (!Files.exists(Path.of(rutaMascotas))) {
            nueva.guardarEnArchivo(rutaMascotas);
        }
    }


    public static void csvMascota() throws IOException, ParseException, CsvValidationException {


        try (CSVReader reader = new CSVReader(new FileReader(rutaMascotas))) {
            String[] nextLine = null;

            while ((nextLine = reader.readNext()) != null) {
                String nombre2=nextLine[0];
                String tipo2=nextLine[1];
                String sexo2=nextLine[2];
                String vacuna=nextLine[3];
                String fecha=nextLine[4];
                String efectividad=nextLine[5];
                mascotas.add(new Mascotas(nombre2, tipo2, sexo2, vacuna, fecha, efectividad));
            }

        } catch (Exception e) {
            System.out.println(e);

        }
    }
    public static void csvPersona() throws IOException, ParseException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(rutaPersonas))) {
            String[] nextLine = null;

            while ((nextLine = reader.readNext()) != null) {
                String nombre2=nextLine[0];
                String tipo2=nextLine[1];
                String sexo2=nextLine[2];
                String vacuna=nextLine[3];
                personal.add(new Personal(nombre2,tipo2,sexo2,vacuna));
            }

        } catch (Exception e) {
            System.out.println(e);

        }
    }







    public static int Validar(int x) {
        int n = -1;
        do {
            //Scanner ponerlo dentro del DO, y dentro de una funcion
            Scanner teclado = new Scanner(System.in);
            //System.out.println("ingrese otro numero");
            try {
                n = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Error");
            }
            if (n < 0 || n > x) {
                System.out.println("ingrese un numero valido");
            }
        } while (n < 0 || n > x);
        return n;
    }

}
