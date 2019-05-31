import lejos.hardware.Button;


public class Uso {
	
	public static void main(String[] args) {
		Veiculo carro =  new Veiculo(true, true, true);
		//seta velocidade do carro
		//carro.setVelocidadeEsteiras(360);
		//anda pra frente por 5s
		//carro.setEsteirasForward(5);
		//toca um som
		//carro.ev3.beep1();

		//anda para tras por 5s
		//carro.setEsteirasBackward(5);
		
		//carro.ev3.beep5();
		//curva para a direita e depois para a esquerda por 2 segundos
		//carro.curvaDireita(2);
//		carro.stop();
		//carro.curvaEsquerda(2);
//		carro.stop();
		
		//abre garra
		//carro.garra.abre();
		//carro.setEsteirasForward(1);
		//carro.garra.fecha();
		
		//carro.recuaAteColidir();
		//carro.segueLinha();
		while(Button.ESCAPE.isUp())
		{
//			System.out.println(carro.iv.coletaAmostra());
//			System.out.println(carro.tq.coletaAmostra(carro.amostras));
//			System.out.println(carro.pb.coletaAmostra(carro.amostras));
			
		}
		carro.ev3.corLed(4);
		carro.fechaPortas();
		
	}

}
