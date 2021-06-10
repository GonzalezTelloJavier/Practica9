package ito.poo.app;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;

import ito.poo.clases.CuentasBanco;
import Texto.ito.poo.Archivos;
import Texto.ito.poo.Documentos;
import ito.poo.excepcion.EliminaCuenta;
import ito.poo.excepcion.ExisteCuenta;
import ito.poo.excepcion.DeptoCuenta;
import ito.poo.excepcion.NoCuenta;
import ito.poo.excepcion.CuentaRetiro;
import ito.poo.excepcion.CuentaSaldo;

public class MyApp {
	
	static CuentasBanco c=new CuentasBanco();
	static Archivos archivo;
	static Documentos archivo2;
	
	static void run() throws HeadlessException, NoCuenta, CuentaSaldo, CuentaRetiro, DeptoCuenta, ExisteCuenta, EliminaCuenta, FileNotFoundException {
		
		App1.menu();
		   
		
    }

		static void crearArchivo() throws FileNotFoundException {
			archivo = new Archivos("guardacuentas");
		}
		public static void main(String[] args) throws HeadlessException, NoCuenta, CuentaSaldo, CuentaRetiro, DeptoCuenta, ExisteCuenta, EliminaCuenta, FileNotFoundException {
			run();
		}
}
	