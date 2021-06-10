package Texto.ito.poo;

import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Obj1 {

	private ObjectOutputStream archivo;
	
	public Obj1(String archivo) throws FileNotFoundException, IOException {
		this.archivo= new ObjectOutputStream(new FileOutputStream(archivo));
	}
	
	public void writeObject(Object item) throws IOException {
		archivo.writeObject(item);
	}
	
	public void close() throws IOException {
		archivo.close();
	}

}