import java.util.ArrayList;

public class Personal {
    private final ArrayList<Mascotas> mascotas=new ArrayList<>();
    private final String nombre;
    private final String apellido;
    private final String rut;
    private final String especialidad;
    private final boolean rutValido;

    public Personal(String nombre, String apellido, String rut,String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.especialidad=especialidad;
        this.rutValido=validarRut(rut);
    }

    public Personal(String nombre, String apellido, String rut) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.rutValido = validarRut(rut);
        especialidad = null;
    }
    private static boolean validarRut(String rut) {   // 12.345.678-k
        boolean valido= false;
        rut = rut.toUpperCase();            // 12.345.678-K
        rut = rut.replace("-", "");         // 12.345.678K
        rut = rut.replace(".", "");         // 12345678k
        int dv = rut.charAt(rut.length()-1);
        try{
            int numRut = Integer.parseInt(rut.substring(0,rut.length()-1));

            int m=0, s=1;
            for (;numRut !=0;numRut/=10) {
                s = (s+numRut%10 *(9-m++%6))%11;
            }

            if(dv==(char)(s!=0 ?s+47:75)){
                valido = true;
            }

        }catch(Exception e){
            System.out.println("rut invalido");
        }

        return valido;
    }
    public void mostrarPersona(){
        System.out.println("-----------");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Apellido: "+this.apellido);
        System.out.println("Rut: "+this.rut);
        System.out.println("Especialidad: "+this.especialidad);
        System.out.println("Rut real?: "+this.rutValido);
        System.out.println("-----------");
    }
    public void guardarEnArchivo(String ruta){
        GestorArchivo ga = new GestorArchivo();
        String contenido = this.nombre+ ","+ this.apellido +","+this.rut+","+this.especialidad;
        ga.nuevaLinea(ruta, contenido);
    }
    public void agregarMascota(Mascotas m0) {
        mascotas.add(m0);
    }
}



