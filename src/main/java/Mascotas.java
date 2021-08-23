import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mascotas {
    private final String nombre;
    private final String tipo; //(gato o perro)
    private final int sexo; //1 Macho 2 Hembra
    private int vadministrada; //Tipo 1 o 2: 0 si no se a vacunado
    private boolean efective;    //true si fue efectiva -false si no se vacuno o no fue efectiva
    private LocalDate fechadv;   //fecha vacuna

    public Mascotas(String nombre, String tipo, String sexo, String vadministrada, String fecha, String efectividad){
        DateTimeFormatter formato =DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.nombre = nombre;
        this.tipo = tipo;
        this.sexo = setSexo(sexo);
        this.vadministrada =Integer.parseInt(vadministrada);
        try{
        this.fechadv= LocalDate.parse(fecha,formato);
        }catch(Exception e){
            this.fechadv=null;
        }
        this.efective=Boolean.parseBoolean(String.valueOf(efectividad));
    }

    public Mascotas(String nombre, String tipo, int sexo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.sexo = sexo;
        this.vadministrada=0;
        this.fechadv=null;
        this.efective=false;
    }

    public String getTipo() {
        return tipo;
    }

    public void setVadministrada(int vadministrada) {
        this.vadministrada = vadministrada;
    }

    public void setEfective(boolean efective) {
        this.efective = efective;
    }

    public void setFechadv(LocalDate fechadv) {
        this.fechadv = fechadv;
    }

    public void VacunarPerro(int vacAdd){
        DateTimeFormatter formato =DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechadv= LocalDate.parse(LocalDate.now().format(formato));
        switch (vacAdd){
            case 1:
                int efect= (int) (Math.random()*100);
                if(efect<=80){
                    setVadministrada(1);
                    setEfective(true);
                    setFechadv(fechadv);
                }else if(efect>81) {
                    setVadministrada(1);
                    setEfective(false);
                    setFechadv(fechadv);
                }
                break;

            case 2:
                efect = (int) (Math.random() * 100);
                if(efect<=95){
                    setVadministrada(1);
                    setEfective(true);
                    setFechadv(fechadv);
                }else if (efect>96){
                    setVadministrada(1);
                    setEfective(false);
                    setFechadv(fechadv);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + vacAdd);
        }
    }


    public void VacunarGato(int vacAdd){
        LocalDate fechadv=LocalDate.now();
        switch (vacAdd){
            case 1:
                int efect= (int) (Math.random()*100);
                if(efect<=90){
                    setVadministrada(1);
                    setEfective(true);
                    setFechadv(fechadv);
                }else if(efect>91) {
                    setVadministrada(1);
                    setEfective(false);
                    setFechadv(fechadv);
                }
                break;

            case 2:
                efect = (int) (Math.random() * 100);
                if(efect<=75){
                    setVadministrada(2);
                    setEfective(true);
                    setFechadv(fechadv);
                }else if (efect>76){
                    setVadministrada(2);
                    setEfective(false);
                    setFechadv(fechadv);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + vacAdd);
        }
    }

    public int setSexo(String a) {
        return switch (a) {
            case "Macho" -> 1;
            case "Hembra" -> 2;
            default -> 0;
        };

    }
    public String mostrarSexo() {
        int a = Integer.parseInt(String.valueOf(sexo));
        return switch (a) {
            case 1 -> "Macho";
            case 2 -> "Hembra";
            default -> null;
        };
    }
    public void mostrarMascota(){
        System.out.println("-----------");
        System.out.println("Nombre: "+ this.nombre);
        System.out.println("Tipo: "+ tipo);
        System.out.println("Sexo: "+mostrarSexo());
        System.out.println("Vacuna administrada: "+ vadministrada);
        System.out.println("Fecha: "+fechadv);
        System.out.println("Efectividad de tratamiento: "+efective);
        System.out.println("-----------");
    }
    public void guardarEnArchivo(String ruta){
        GestorArchivo ga = new GestorArchivo();
        String contenido = this.nombre+ ","+ tipo +","+mostrarSexo()+","+ vadministrada+","+ fechadv +","+ efective;
        ga.nuevaLinea(ruta, contenido);
    }
}
