package ito.poo.clases;
import interfaces.ito.poo.Arreglo;
import ito.poo.clases.CuentaBancaria;
import ito.poo.excepcion.CuentaRetiro;
import ito.poo.excepcion.DeptoCuenta;
import ito.poo.excepcion.EliminaCuenta;
import ito.poo.excepcion.ExisteCuenta;

public class CuentasBanco implements Arreglo<CuentaBancaria> {

	static CuentaBancaria e;

	private CuentaBancaria cuentas[]=null;
	private int ultimo=0;
	private final int INC=5;
	
	public CuentasBanco() {
		super();
		this.cuentas=new CuentaBancaria[INC];
		this.ultimo=-1;
	}
	
	public void DeptoCuenta(float DeptoCuenta) throws DeptoCuenta{
		if (DeptoCuenta<500F||DeptoCuenta>25000F)
			throw new DeptoCuenta("¡¡¡ERROR!!!Deposita una cantidad entre $500 y $25000!");
	}
	public void CuentaRetiro(float cuentaretiro) throws CuentaRetiro{
		if (!((cuentaretiro%100)==0) || cuentaretiro<100 || cuentaretiro>6000)
			throw new CuentaRetiro("¡¡¡Error!!!No se puede retirar mas de $6000!\n Intente con una cantidad mayor a $100 y mayor que $6000 \n Su retiro debe ser divisible  entre 100");
	}
	
	public void ExisteCuenta(CuentaBancaria item) throws ExisteCuenta{
		if(this.existeItem(item)) {
			throw new ExisteCuenta("¡¡¡ERROR!!!La cuenta que intenta registrar ya existe \n Intente de nuevo");
		}
	}
	
	public void EliminaCuenta(CuentaBancaria item) throws EliminaCuenta{
		if(item.getCuentaSaldo()==0) {
			throw new EliminaCuenta("¡¡¡ERROR!!!No se puede eliminar una cuenta con saldo o con deudas pendientes! \n Si el problema persiste pida atencion en ventanilla");
		}
	}
	
	public void incrementaArreglo() {
		CuentaBancaria aumenta[]=new CuentaBancaria[this.cuentas.length+INC];
		for(int i=0;i<cuentas.length;i++)
			aumenta[i]=this.cuentas[i];
		cuentas= aumenta;
	}
	
	@Override
	public boolean addItem(CuentaBancaria item) throws ExisteCuenta {
		boolean add=false;
		
		if(this.isFull()) 
			incrementaArreglo();
		int j=0;
		for(;j<=this.ultimo;j++)
			if(item.compareTo(this.cuentas[j])<0) {
				break;
				}
				
				for(int i=this.ultimo;i>=j;i--)
					cuentas[i+1]=cuentas[i];
				this.cuentas[j]=item;
				this.ultimo++;
				add=true;
			
		return add;
	}

	public boolean existeItem(CuentaBancaria item) {
		boolean existe=false;
		for(int i=0;i<=this.ultimo;i++)
			if(item.compareTo(this.cuentas[i])==0) {
				existe=true;
				break;
			}
					
		return existe;
	}
	
	

	@Override
	public CuentaBancaria getItem(int pos) {
		CuentaBancaria cb=null;
		if(pos<=this.ultimo&&!this.isFree())
			cb=this.cuentas[pos];
		return cb;
	}

	@Override
	public int getSize() {
		return this.ultimo+1;
	}

	@Override
	public boolean clear(CuentaBancaria item) {
		boolean elimina=false;
		if(this.existeItem(item)) {
			int i=0;
			for(;i<=this.ultimo;i++)
				if(item.compareTo(this.cuentas[i])==0)
					break;
			for(;i<=this.ultimo;i++)
				cuentas[i]=cuentas[i+1];
			this.ultimo--;
			elimina=true;
		}
		return elimina;
	}

	@Override
	public boolean isFree() {
		return this.ultimo==-1;
	}

	@Override
	public boolean isFull() {
		
		return this.ultimo+1==this.cuentas.length;
	}
	
	

}