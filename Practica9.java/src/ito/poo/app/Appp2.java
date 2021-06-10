package ito.poo.app;
import java.awt.HeadlessException;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ito.poo.clases.CuentaBancaria;
import ito.poo.clases.CuentasBanco;
import Texto.ito.poo.Obj1;
import Texto.ito.poo.Obj2;
import ito.poo.excepcion.EliminaCuenta;
import ito.poo.excepcion.ExisteCuenta;
import ito.poo.excepcion.DeptoCuenta;
import ito.poo.excepcion.NoCuenta;
import ito.poo.excepcion.CuentaRetiro;
import ito.poo.excepcion.CuentaSaldo;

public class Appp2 {
	
	static CuentasBanco c;
	static CuentaBancaria e;
	static Obj1 outputFile;
	static Obj2 inputFile=null;

	static void menu() throws NoCuenta, CuentaSaldo, CuentaRetiro, DeptoCuenta, HeadlessException, ExisteCuenta, EliminaCuenta {
		Comenzar();
		IniciarP();
		final JPanel panel=new JPanel();
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
		case 3:Retiro();break;
		case 4:AddCuenta();break;
		case 5:EnsenaCuenta();break;
		case 6:EliminaCuenta();break;
		case 7:ciclo=false;error=false;break;
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
		guardaCuentas ();
	}
	static CuentaBancaria capturarCuenta() throws NoCuenta,CuentaSaldo {
		CuentaBancaria n=new CuentaBancaria();
		long l;String fecha,nombre;float CuentaSaldo;
		l=Long.parseLong(JOptionPane.showInputDialog("Inserte el numero de cuenta"));
		nombre=JOptionPane.showInputDialog("Inserte el nombre del dueño:");
		CuentaSaldo=Float.parseFloat(JOptionPane.showInputDialog("Saldo inicial"));
		fecha=JOptionPane.showInputDialog("Fecha en que esta creando su cuenta??\\nFavor de insertar:(año-mes-día):");
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
			boolean CUENTAS=true;
			while(CUENTAS) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta a la cual depositara\\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Ingrese el monto que desea depositar"));
		    c.DeptoCuenta(cantidad);
		    c.getItem(pos-1).setCuentaSaldoActualizado(c.getItem(pos-1).getCuentaSaldo()+cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"Su deposito ha sido exitoso");
		    CUENTAS=false;
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"¡¡¡Advertencia!!!Su cuenta no esta registrada!");
			}
		}
	}
	
	static void Retiro() throws CuentaSaldo, CuentaRetiro {
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Aun no hay cuentas existentes");
		else {
			boolean CUENTAS=true;
			while(CUENTAS) {
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
			boolean CUENTAS=true;
			while(CUENTAS) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta a eliminar\\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    	c.EliminaCuenta(c.getItem(pos-1));
		    	c.clear(c.getItem(pos-1));
		    	JOptionPane.showMessageDialog(null,"Cuenta eliminada con exito");
		    	CUENTAS=false;
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
		default:JOptionPane.showMessageDialog(null,"Ingrese aqui la opción que quiera");
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

	static void guardaCuentas() {
		if(c.isFree()) {
			
		}
		else {
			try {
				outputFile = new Obj1("datosconCuentas");
			    for(int i=0;i<c.getSize();i++)
			    	outputFile.writeObject(c.getItem(i));
			    outputFile.close();
			}catch(Exception e) {
				
			}
		}
	}
	
	static void IniciarP() throws ExisteCuenta{
		boolean hay=false;
		try {
			inputFile = new Obj2("datosconCuentas");    
		    hay=true;  
		}catch(IOException e) {
			System.err.println("Sin datos, creando...");
		}
		if(hay)
			grabarelRegistro();
	}
	
	static void grabarelRegistro() throws ExisteCuenta {
		try {
		      inputFile = new Obj2("datosconCuentas");
		      while(true) {
			      c.addItem((CuentaBancaria)inputFile.readObject());
		      }
		}catch(IOException e) {
			
			try {
				inputFile.close();
				System.out.println("Cuentas en sistema");
			} catch (IOException e1) {
				
			}
		}
		catch(ClassNotFoundException e) {
			
		}
	}
	
}