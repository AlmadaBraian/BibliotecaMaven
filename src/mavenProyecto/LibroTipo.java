package mavenProyecto;

public enum LibroTipo {
    NOVELA("novela"), TEATRO("teatro"), POESIA("poesia"), ENSAYO("ensayo");
	
    private LibroTipo(String tipo) { 

    	this.tipo = tipo; 
    }
    
    public String getTipo() { 
    	return tipo; 
    }
    
    private String tipo;
}
