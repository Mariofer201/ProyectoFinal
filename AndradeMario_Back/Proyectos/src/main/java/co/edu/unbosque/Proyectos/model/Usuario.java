package co.edu.unbosque.Proyectos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String username;
    private String contraseña;
    private double compraracciones;
    private double venderacciones;
    public double getCompraacciones() {
		return compraracciones;
	}

	public double getCompraracciones() {
		return compraracciones;
	}

	public void setCompraracciones(double compraracciones) {
		this.compraracciones = compraracciones;
	}

	public double getVenderacciones() {
		return venderacciones;
	}

	public void setVenderacciones(double venderacciones) {
		this.venderacciones = venderacciones;
	}

	private int acciones;

    public int getAcciones() {
		return acciones;
	}

	public void setAcciones(int acciones) {
		this.acciones = acciones;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
	}

}