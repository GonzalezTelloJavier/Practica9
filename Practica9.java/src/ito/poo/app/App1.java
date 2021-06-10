package ito.poo.app;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import ito.poo.excepcion.EliminaCuenta;
import ito.poo.excepcion.ExisteCuenta;
import ito.poo.excepcion.DeptoCuenta;
import ito.poo.excepcion.NoCuenta;
import ito.poo.excepcion.CuentaRetiro;
import ito.poo.excepcion.CuentaSaldo;
import ito.poo.clases.CuentaBancaria;
import ito.poo.clases.CuentasBanco;
import Texto.ito.poo.Archivos;
import Texto.ito.poo.Documentos;
import Texto.ito.poo.Obj1;
import Texto.ito.poo.Obj2;


public class App1 {

	static CuentasBanco c;
	static CuentaBancaria e;
	static Archivos archivo;
	static Documentos archivo2;
	
	static void menu() throws NoCuenta, CuentaSaldo, CuentaRetiro, DeptoCuenta, HeadlessException, ExisteCuenta, EliminaCuenta, FileNotFoundException {
		Comenzar();
		IniciarP();
		final JPanel 
		panel=new JPanel();
		boolean error=true;
		while(error) {
		try {
		boolean ciclo=true;
		int respuesta=0;
		while(ciclo) {
		String opciones="Seleccione la opccion que desee\n 1)Consulte su saldo disponible\n 2)Depositar a una cuenta\n 3)Retirar saldo en efectivo\n"
				+  " 4)Agregar nueva cuenta\n 5)Mostrar cuentas existentes \n 6)Eliminar cuenta\n 7)Exit";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		
		switch(respuesta) {
		case 1:Consultar();break;
		case 2:Depositar();break;
		case 3:Retirar();break;
		case 4:AddCuenta();break;
		case 5:EnsenaCuenta();break;
		case 6:EliminaCuenta();break;
		case 7:guardaRegistro();ciclo=false;error=false;break;
		default:JOptionPane.showMessageDialog(null,"Ingresar opcion");
		  }
		}
		
		}catch(NoCuenta e){
			
			JOptionPane.showMessageDialog(panel,e.getMessage(),"¡¡¡ERROR!!!", JOptionPane.ERROR_MESSAGE);
			
		}catch(CuentaSaldo e) {
			
			JOptionPane.showMessageDialog(panel,e.getMessage(),"¡¡¡ERROR!!!", JOptionPane.ERROR_MESSAGE);
			
		}catch(DeptoCuenta e) {
			
			JOptionPane.showMessageDialog(panel,e.getMessage(),"¡¡¡ERROR!!!", JOptionPane.ERROR_MESSAGE);
			
		}catch(CuentaRetiro e) {
			
			JOptionPane.showMessageDialog(panel,e.getMessage(),"¡¡¡ERROR!!!", JOptionPane.ERROR_MESSAGE);
			
		}catch(ExisteCuenta e) {
			
			JOptionPane.showMessageDialog(panel,e.getMessage(),"¡¡¡ERROR!!!", JOptionPane.ERROR_MESSAGE);
			
		}catch(EliminaCuenta e) {
			
			JOptionPane.showMessageDialog(panel,e.getMessage(),"¡¡¡ERROR!!!", JOptionPane.ERROR_MESSAGE);
			
		}
		}
	}
	
	static CuentaBancaria capturarCuenta() throws NoCuenta,CuentaSaldo {
		CuentaBancaria n=new CuentaBancaria();
		long l;String fecha,nombre;float CuentaSaldo;
		l=Long.parseLong(JOptionPane.showInputDialog("Inserte el numero de cuenta"));
		nombre=JOptionPane.showInputDialog("Inserte el nombre del dueño:");
		CuentaSaldo=Float.parseFloat(JOptionPane.showInputDialog("Saldo inicial"));
		fecha=JOptionPane.showInputDialog("Fecha en que esta creando su cuenta??\nFavor de insertar:(año-mes-día):");
		n.setNoCuenta(l);
		n.setNomCliente(nombre);
		n.setCuentaSaldo(CuentaSaldo);
		n.setFechaApertura(LocalDate.parse(fecha));
		return n;
	}
	
	static void Comenzar() {
		c=new CuentasBanco();
	}
	
	static void AddCuenta() throws NoCuenta, CuentaSaldo, HeadlessException, ExisteCuenta {
		CuentaBancaria nueva;
		nueva=capturarCuenta();
		c.ExisteCuenta(nueva);
		c.addItem(nueva);
	    JOptionPane.showMessageDialog(null,"Su cuenta se creo con exito!!");
			if(c.isFull())
				c.incrementaArreglo();
		
	}
	
	static void EnsenaCuenta() {
		
		
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"No se encontro ninguna cuenta");
		else {
			
		String cuentas="";
		for(int i=0;i<c.getSize();i++)
			cuentas=cuentas+"\n"+(c.getItem(i));
		JOptionPane.showMessageDialog(null,cuentas);
		
		}
	}
	
	static void Depositar() throws CuentaSaldo, DeptoCuenta {
		
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			
			JOptionPane.showMessageDialog(null,"Aun no hay cuentas existentes");
		
		else {
			
			
			boolean CUENTA=true;
			while(CUENTA) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta a la cual depositara\n"+cuentas));
		    
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Ingrese el monto que desea depositar"));
		    c.DeptoCuenta(cantidad);
		    
		    c.getItem(pos-1).setCuentaSaldoActualizado(c.getItem(pos-1).getCuentaSaldo()+cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"Su deposito ha sido exitoso");
		    CUENTA=false;
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"¡¡¡Advertencia!!!Su cuenta no esta registrada!");
			}
		}
	}
	
	static void Retirar() throws CuentaSaldo, CuentaRetiro {
		
		int pos=0;
		float cantidad=0;
		
		if(c.isFree())
			
			JOptionPane.showMessageDialog(null,"Aun no hay cuentas registradas");
		
		else {
			
			boolean CUENTA=true;
			while(CUENTA) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Sellecione su cuenta para hacer el retiro\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    	
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Cantidad"));
		    c.CuentaRetiro(cantidad);
		    
		    if(!(c.getItem(pos-1).getCuentaSaldo()<cantidad)) {
		    	
		    c.getItem(pos-1).setCuentaSaldoActualizado(c.getItem(pos-1).getCuentaSaldo()-cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    
		    	JOptionPane.showMessageDialog(null,"Lo sentimos usted no cuenta con el saldo suficiente");
		    }
		    }
		    
			}
		}
	}
	
	static void EliminaCuenta() throws EliminaCuenta {
		int pos=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Aun no hay cuentas");
		else {
			boolean CUENTA=true;
			while(CUENTA) {
		    String cuentas="";
		    
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta a eliminar\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    	c.EliminaCuenta(c.getItem(pos-1));
		    	c.clear(c.getItem(pos-1));
		    	
		    	JOptionPane.showMessageDialog(null,"Cuenta eliminada con exito");
		    	CUENTA=false;
		    }	
		    else
		    	
		    	JOptionPane.showMessageDialog(null,"Aun no hay cuentas, no se pueden eliminar");
		  }
		}
	}
	
	static void Consultar() {
		int respuesta=0;
		boolean ciclo=true;
		while(ciclo) {
			
		String opciones="Que accion desea realizar:\n 1)Cuentas con saldo maximoimo\n 2)Cuentas con saldo minimo\n"+ " 3)Saldo total de las cuentas\n"
				+ " 4)Cuentas con mas de $10,000\n 5)Promedio de todas las cuentas\n 6)Exit";
		
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:cuentasmaximoimas();ciclo=false;break;
		case 2:cuentasMinimas();ciclo=false;break;
		case 3:cuentasTotal();ciclo=false;break;
		case 4:Cuentas10();ciclo=false;break;
		case 5:promCuentas();ciclo=false;break;
		case 6:ciclo=false;break;
		default:JOptionPane.showMessageDialog(null,"Inserte la opcion que desee");
		  }
		}
	}
	
	static void cuentasTotal() {
		
		
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Aun no tenemos cuentas registradas");
		else {
		    float cuentasTotal=0;
		    for(int i=0;i<c.getSize();i++) 
		    	
			    cuentasTotal=cuentasTotal+c.getItem(i).getCuentaSaldo();
		    JOptionPane.showMessageDialog(null,"Monto total!: $"+cuentasTotal);
		}
	}
	
	static void promCuentas() {
		float montoProm=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Aun no tenemos cuentas registradas");
		else {
			
		    float cuentasTotal=0;
		    
		    for(int i=0;i<c.getSize();i++) 
		        cuentasTotal=cuentasTotal+c.getItem(i).getCuentaSaldo();
		    montoProm=cuentasTotal/c.getSize(); 
		    JOptionPane.showMessageDialog(null,"Monto Promedio: $"+montoProm);
		    
		}
	}
	
	static void Cuentas10() {
		
		if(c.isFree())
			
			JOptionPane.showMessageDialog(null,"Aun no tenemos cuentas registradasa");
		else {
			int voidd=0;
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCuentaSaldo()>10000) 
					copia[i-voidd]=c.getItem(i);
				else
					voidd++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-voidd);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"Cuentas con más de $10,000:\n"+cuentas);
			
		}
	}
	
	static void cuentasmaximoimas() {
		
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Aun no tenemos cuentas registradasa");
		else {
			
			int voidd=0;
			float maximo=c.getItem(0).getCuentaSaldo();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCuentaSaldo()>maximo)
					maximo=c.getItem(i).getCuentaSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCuentaSaldo()==maximo) 
					copia[i-voidd]=c.getItem(i);
				else
					voidd++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-voidd);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"Cuentas con saldo maximo:\n"+cuentas);
			
		}
		
	}
	
	static void cuentasMinimas() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Aun no tenemos cuentas registradasa");
		else {
			int voidd=0;
			float min=c.getItem(0).getCuentaSaldo();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCuentaSaldo()<min)
					min=c.getItem(i).getCuentaSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCuentaSaldo()==min) 
					copia[i-voidd]=c.getItem(i);
				else
					voidd++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-voidd);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"Cuentas con saldo minimo:\n"+cuentas);
		}
	}

	static void guardaRegistro() throws FileNotFoundException {
		if (c.isFree()) {
			
		}
		else {
			archivo=new Archivos("Ahorra cuentas");
			for(int i=0;i<c.getSize();i++) {
				archivo.writeLong(c.getItem(i).getNoCuenta());
				archivo.writeString(c.getItem(i).getNumCliente());
				archivo.writeFloat(c.getItem(i).getCuentaSaldo());
				if(c.getItem(i).getFechaActualizacion()==null) {
					archivo.writeString(c.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn("null");	
				}
				else {
					archivo.writeString(c.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn(c.getItem(i).getFechaActualizacion().toString());	
				}
				
			}
			archivo.close();
		}
					
	}
	
	static void IniciarP() throws FileNotFoundException, NoCuenta, ExisteCuenta {
		boolean hay=false;
		try {
			archivo2= new Documentos("Ahorra cuentas");
			hay=true;
		}catch(FileNotFoundException e) {
			System.err.println("Creando cuentas...");
		}
		if(hay)
			LTexto();
		
	}
	
static void LTexto() throws NoCuenta, ExisteCuenta {
		
		while(!archivo2.isEOF()) {
			e=new CuentaBancaria();
			e.setNoCuenta(archivo2.readLong());
			e.setNomCliente(archivo2.readString());
			e.setCuentaSaldoActualizado(archivo2.readFloat());
			e.setFechaApertura(LocalDate.parse(archivo2.readString()));
			String fechaAct;
			fechaAct=archivo2.readString();
			if(fechaAct.equals("null")) 
				e.setFechaActualizacion(null);
			else
				e.setFechaActualizacion(LocalDate.parse(fechaAct));
			c.addItem(e);
			archivo2.isFinLinea();
		
			
		}
	}

}