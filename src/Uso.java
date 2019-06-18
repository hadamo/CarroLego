import lejos.hardware.Button;

public class Uso {

	public static void main(String[] args)
	{
		Veiculo carro = new Veiculo(false, true,true, false);
		while(Button.ESCAPE.isUp())
		{
			carro.coletaAmostras();
			System.out.println("dir"+carro.amostras[0]+" esq" + carro.amostras[1]);
		}
		
		/*carro.setVelocidadeEsteirasGrau(300);
		carro.setEsteirasForward(3);
		carro.ev3.beep3();
		carro.setEsteirasBackward(3);
		carro.ev3.beep3();
		carro.curvaDireita(2);
		carro.ev3.beep1();
		carro.curvaEsquerda(2);
		carro.ev3.beep4();
		
		
		carro.segueLinha("direito");
		
		
		carro.recuaAteColidir();
		
		
		int comando = 0;
		while(comando!=9)
		{
			comando = carro.getComando();
			if(comando == 1) carro.setEsteirasForward();
			if(comando == 2) carro.setEsteirasBackward();
			if(comando == 3) carro.curvaDireita();
			if(comando == 4) carro.curvaEsquerda();
			if(comando == 5 && carro.garra.isFechada()) carro.abreGarra();
			if(comando == 6 && carro.garra.isAberta()) carro.fechaGarra();
			if(comando == 7) carro.stop();
		}
		*/
		carro.ev3.beep5();
		carro.ev3.corLed(3);
	}
	
}