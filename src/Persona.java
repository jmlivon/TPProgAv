import anotaciones.Tabla;
import anotaciones.Columna;
import anotaciones.Dni;

@Tabla(nombre = "persona")
public class Persona {
	
	@Dni
	private Integer dni;
	@Columna(nombre = "nombre")
	private String nombre;
	
	public Persona() {
		
	}
	
	public Persona(String nombre) {
		super();
		//this.dni = dni;
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + "]";
	}	
}
