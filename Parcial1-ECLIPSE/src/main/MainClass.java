package main;
import java.util.ArrayList;

import processing.core.PApplet;

public class MainClass extends PApplet implements OnMessagesListener{

	private Connection tcp;
	private int x,y,r,g,b, counter;
	private String msg, previaOconfirmar;
	private Recordatorio record;
	private ArrayList<Recordatorio> posts;
	private boolean estaEnPrev;

	public static void main(String[] args) {
		PApplet.main("main.MainClass");
	}

	public void settings() {
		size(800, 600);
	}

	
	public void setup() {
		tcp = Connection.getInstance();
		tcp.setObserver(this);
		posts = new ArrayList<Recordatorio>();
		previaOconfirmar = "1";
		estaEnPrev = false;
		counter = 0;

	}

	public void draw() {
		if (previaOconfirmar.equals("2")) {
			estaEnPrev = true;
		}
		switch (previaOconfirmar) {
		case "1": {
			if(estaEnPrev) {
				for (int i = 0; i < counter; i++) {
					int index = posts.size()-2;
					posts.remove(index);
				}
				counter = 0;
				estaEnPrev = false;
			}
			textAlign(CENTER);
			background(167, 246, 134);
			for (int i = 0; i < posts.size(); i++) {
				posts.get(i).drawRecor();
			}
		}
		break;

		case "2": {
		
			background(167, 246, 134);
			textAlign(CENTER);
			int index = posts.size()-1;
			posts.get(index).vistaPrev();
			fill(255,0,0);
			rect(350, 550, 100, 40);
			fill (255,255,255);
			text("VISTA PREVIA", 400, 20);
			text("volver", 350 +30, 550+20);			
		}
		break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + previaOconfirmar);
		}


	}
	@Override
	public void OnMessage(String msg) {
		String [] mensaje = msg.split(",");
		x = Integer.parseInt(mensaje[1]);
		y = Integer.parseInt(mensaje[2]);
		String color = mensaje[0];
		color(color);
		msg = mensaje[3];
		previaOconfirmar = mensaje[4];
		posts.add(new Recordatorio(x, y, r, g, b, msg, this));
		if (previaOconfirmar.equals("2")) {
			counter = counter +1;
		}
	}

	public void color(String color) {
		switch (color) {
		case "verde": {
			r = 0;
			g=255;
			b=0;

		}

		break;

		case "amarillo": {
			r = 255;
			g = 233;
			b = 0;

		}
		break;
		case "rojo": {
			r = 255;
			g=0;
			b=0;

		}
		break;
		default:

		} 
	}

	public void mouseClicked() {
		if (mouseX > 350 && mouseX < 350 + 100
				&& mouseY > 550 && mouseY < 550+40) // 	Contacts Button
			previaOconfirmar = "1";
		eliminarPrev();
	}
	
	public void eliminarPrev() {
		for (int i = 0; i < counter; i++) {
			int index = posts.size()-1;
			posts.remove(index);
		}
		counter = 0;
		estaEnPrev = false;
	}
}