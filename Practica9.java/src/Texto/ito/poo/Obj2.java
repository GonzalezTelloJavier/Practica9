package Texto.ito.poo;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Obj2 {

	private ObjectInputStream archivo;
	
	public Obj2(String archivo) throws FileNotFoundException, IOException {
		this.archivo= new ObjectInputStream(new FileInputStream(archivo));
	}
	
	public Object readObject() throws ClassNotFoundException, IOException {
		Object aux=null;
		aux= archivo.readObject();
		return aux;
	}
	
	public void close() throws IOException {
		archivo.close();
	}
}