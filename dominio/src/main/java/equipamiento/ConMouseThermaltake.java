package equipamiento;

import personaje.*;

public class ConMouseThermaltake extends PersonajeEquipado{
	public ConMouseThermaltake(Personaje personajeDecorado) {
		super(personajeDecorado);
	}
	
	@Override
	public int obtenerPuntosDeFuerza() {
		return super.obtenerPuntosDeFuerza() *2;
	}


}
