package mavenProyecto;

public enum estadoCopia {
    PRESTADO("prestado"), RETRASO("retraso"), BIBLIOTECA("biblioteca"), REPARACION("REPARACION");
	
    estadoCopia(String estado) { 

    	this.estado = estado; 
    }
    
    public String getEstado() { 
    	return estado; 
    }
    
    private String estado;
}
