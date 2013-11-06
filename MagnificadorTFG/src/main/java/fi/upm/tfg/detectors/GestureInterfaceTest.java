package fi.upm.tfg.detectors;

import android.view.MotionEvent;

import fi.upm.tfg.magnificador.MagnificadorProcess;

public interface GestureInterfaceTest {

	//Método encargado de capturar los eventos de pulsación
	public boolean onTouchEvent(MotionEvent event, MagnificadorProcess mView);
	/*//Método encargado de imprimir un mensaje por pantalla
	public void TextMessage(String texto);*/
	
	
	
	
}
