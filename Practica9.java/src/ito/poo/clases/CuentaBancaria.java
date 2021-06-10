package ito.poo.clases;
import java.time.LocalDate;

import ito.poo.excepcion.CuentaSaldo;
import ito.poo.excepcion.NoCuenta;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CuentaBancaria implements Comparable<CuentaBancaria>, Serializable {
	static CuentasBanco c;
	
	private long noCuenta = 0L;
	private String noCliente = "";
	private float cuentaSaldo = 0F;
	private LocalDate fechaApertura = null;
	private LocalDate fechaActualizacion = null;
	
	public CuentaBancaria() {
		
		super();
		
	}
	
	private void NoCuenta(long noCuenta) throws NoCuenta{
		if (9999>=noCuenta)
			throw new NoCuenta("¡¡¡¡ERROR!!!Solo cuentas mayores a 9999");
	}
	
	private void CuentaSaldo(float saldo) throws CuentaSaldo{
		if (saldo<5000F)
			throw new CuentaSaldo("PORFAVOR INTENTE DE NUEVO! El monto para crear una cuenta debe ser mayor o igual a $5000! ");
	}
	
	
	
	
	public CuentaBancaria(long noCuenta, String noCliente, float CuentaSaldo, LocalDate fechaApertura)
			throws NoCuenta,CuentaSaldo {
		super();
		NoCuenta(noCuenta);
		this.noCuenta = noCuenta;
		this.noCliente = noCliente;
		CuentaSaldo(cuentaSaldo);
		cuentaSaldo = CuentaSaldo;
		this.fechaApertura = fechaApertura;
	}
	
	public boolean Deposito(float Cantidad)throws CuentaSaldo {
		boolean Deposito = false;
		if(this.fechaApertura==null)
			System.out.println("¡¡¡ERROR!!!Cuenta no valida o no disponible por el momento");
		else {
			Deposito = true;
			this.setCuentaSaldo(this.getCuentaSaldo()+Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		
		return Deposito;
	}
	

	public boolean Retiro(float Cantidad)throws CuentaSaldo {
		
		boolean Retiro = false;
		if(Cantidad<=this.getCuentaSaldo()) {
			Retiro = true;
			this.setCuentaSaldo(this.getCuentaSaldo()-Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		else
			System.out.println("¡¡¡ERROR!!!Su saldo es insuficiente!");
		return Retiro;
	}

	
	public long getNoCuenta() {
		
		return this.noCuenta;
	}

	public void setNoCuenta(long newNumCuenta)throws NoCuenta {
		NoCuenta(newNumCuenta);
		this.noCuenta = newNumCuenta;
	}

	
	public String getNumCliente() {
		return this.noCliente;
	}

	
	public void setNomCliente(String newNumCliente) {
		this.noCliente = newNumCliente;
	}

	public float getCuentaSaldo() {
		return this.cuentaSaldo;
	}

	public void setCuentaSaldo(float newcuentaSaldo)throws CuentaSaldo {
		CuentaSaldo(newcuentaSaldo);
		this.cuentaSaldo = newcuentaSaldo;
	}
	
	public void setCuentaSaldoActualizado(float newcuentaSaldo) {
		this.cuentaSaldo = newcuentaSaldo;
	}

	public LocalDate getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(LocalDate newFechaApertura) {
		this.fechaApertura = newFechaApertura;
	}

	public LocalDate getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate newFechaActualizacion) {
		this.fechaActualizacion = newFechaActualizacion;
	}

	@Override
	public String toString() {
		return "CuentaBancaria [noCuenta=" + noCuenta + ", nomCliente=" + noCliente + ", cuentaSaldo=" + cuentaSaldo
				+ ", fechaApertura=" + fechaApertura + ", fechaActualizacion=" + fechaActualizacion + "]";
	}
	@Override
	public int compareTo(CuentaBancaria o) {
		int compare=0;
		if (this.noCuenta<o.getNoCuenta())
			compare=-1;
		else if(this.noCuenta>o.getNoCuenta())
			compare=1;
		return compare;
		
	}


}
