package personaje;

public abstract class Personaje implements Atacable {
	protected String nombre;
	protected int salud=100;
	protected int energia=50;
	protected int fuerza=1;
	protected int ingenio=1;
	protected int defensa=0;
	protected int multavsalud=1;
	protected int modavsalud=0;
	protected float multavenergia=1;
	protected int modavenergia=0;
	protected float multavingenio=1;
	protected int modavingenio=0;
	protected float multavfuerza=1;
	protected int modavfuerza=0;
	protected float multavdefensa=1;
	protected int modavdefensa=0;
	
	protected boolean sexo; //= mucho
	protected int nivel = 1;
	protected int experiencia = 0;
	protected String SpritePath;
	
	public abstract String getSpritepath();
	public final boolean atacar(Atacable atacado) {
		if (puedeAtacar() && atacado.estaVivo()) {
			atacado.serAtacado(this.obtenerPuntosDeFuerza());
			energia -= this.obtenerPuntosDeFuerza(); // Te cansas despues de atacar
			if(!atacado.estaVivo())
				despuesDeAtacar(atacado.darExperiencia());
			return true;
		}
		return false;
	}

	// Despues de atacar me fijo si subio de nivel con la experiencia obtenida
	// si paso de nivel, la experiencia se me resetea a 0, y me tiene que sumar
	// el resto de experiencia que sobro.
	// sino solamente me suma la experiencia obtenida.
	
	protected void despuesDeAtacar(int experienciaObtenida) {
		if( this.experiencia + experienciaObtenida >= this.getTopeExperienciaNivel()){
			this.experiencia = this.experiencia + experienciaObtenida - getTopeExperienciaNivel();
			subirDeNivel();
		}
		else{
			this.experiencia += experienciaObtenida;
		}
	}
	
	protected void subirDeNivel(){
		if(this.nivel < 32){
			this.nivel++;
		}
	}
	
	//HACER METODO REVIVIR
	//METODO MORIR?? dropea items y luego revive, etc
	//La ENERGIA tendria que subirse cada tanto tiempo si no nunca lo va a terminar de matar
	
	public void revivir() {
		this.serCurado();
		this.serEnergizado();
	}
	
	public abstract boolean puedeAtacar();
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	@Override
	public void serAtacado(int da�o) {
		if ( ( da�o - (this.obtenerPuntosDeDefensa()) /10 ) > 0)
			this.salud -= (da�o - (this.obtenerPuntosDeDefensa()) /10 );
	}


	public void serCurado() {
		this.salud = this.getmaxsalud(); //por default pongo el maximo
	}

	public void serEnergizado() {
		this.energia = this.getmaxenergia(); // por default pongo el maximo
	}
	
	public int getSalud() {
		return this.salud;
	}
	
	//Las Siguientes funciones calculan el maximo de los attributos, no los valores actuales. 
	///////////////////////
	public int getmaxsalud(){
		return (int)(100 * this.nivel);
	}
	///////////////////////
	public int getmaxenergia(){
		return (int)(100 * this.nivel + 10);
	}
	/////////////////////////
	public int obtenerPuntosDeFuerza() {
		return (int)(this.fuerza * this.multavfuerza + this.modavfuerza); // Modificadores + valor inherente
	}
	/////////////////////////////
	public int obtenerPuntosDeSalud() {
		return (int)(getmaxsalud() * this.multavsalud + this.modavsalud); // Modificadores + valor inherente
	}
	///////////////////////
	public int obtenerPuntosDeEnergia() {
		return (int)(getmaxenergia() * this.multavenergia + this.modavenergia); // Modificadores + valor inherente
	}
	///////////////
	public int obtenerPuntosDeIngenio() {
		return (int)(this.ingenio * this.multavingenio + this.modavingenio); // Modificadores + valor inherente
	}
	///////////////
	public int obtenerPuntosDeDefensa(){
		return (int)(this.defensa * this.multavdefensa + this.modavdefensa); // Modificadores + valor inherente
	}

	@Override
	public int darExperiencia() {
		return  getExperiencia()/10; //Es igual al anterior em el caso default, y es compatible con otras formulas de experiencia.
	}

	public int getTopeExperienciaNivel() {
		return this.nivel * 100;
	}


	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getNivel() {
		return nivel;
	}

	
	
	
}


