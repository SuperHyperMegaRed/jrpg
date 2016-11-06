package equipamiento;

import personaje.*;

public class ConMonitorBenq extends PersonajeEquipado{
	public ConMonitorBenq(Personaje personajeDecorado) {
		super(personajeDecorado);
	}
	
	@Override
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() +4;
	}
}