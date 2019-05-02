
public class Uso {

	public static void main(String[] args) {
		Veiculo carro =  new Veiculo();
		//seta velocidade do carro
		carro.setVelocidadeEsteiras(360);
		//anda pra frente por 5s
		carro.setEsteirasForward(5);
		
		//anda para tras por 5s
		carro.setEsteirasBackward(5);
		
		//curva para a direita e depois para a esquerda
		carro.curvaDireita();
		carro.stop();
		carro.curvaEsquerda();
		carro.stop();
		
		//abre garra
		carro.garra.abre();
		carro.setEsteirasForward(1);
		carro.garra.fecha();
		
		carro.fechaPortas();
		
	}

}
