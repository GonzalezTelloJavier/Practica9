package ito.poo.app;

import java.awt.HeadlessException;
import ito.poo.clases.CuentasBanco;
import ito.poo.excepcion.EliminaCuenta;
import ito.poo.excepcion.ExisteCuenta;
import ito.poo.excepcion.DeptoCuenta;
import ito.poo.excepcion.NoCuenta;
import ito.poo.excepcion.CuentaRetiro;
import ito.poo.excepcion.CuentaSaldo;

public class MyApp2 {
	
	static CuentasBanco c;
	public static void main(String[] args) throws HeadlessException, NoCuenta, CuentaSaldo, CuentaRetiro, DeptoCuenta, ExisteCuenta, EliminaCuenta {
		Appp2.menu();
	}

}