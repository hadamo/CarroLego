import lejos.hardware.Button;

public class Uso {
	
	public static void main(String[] args) {
		/*
		 * Cria instancia de carro e inicia ela com
		 * 2 esteiras, garra e todos sensores ativos
		 */
		Veiculo carro =  new Veiculo(true, true, true);
		/*
		 * Cria instancia de carro e inicia ela com
		 * 2 esteiras, garra e sensor de toque ativo
		 */
//		Veiculo carro = new Veiculo(true,false,false);
		/*
		 * Cria instancia de carro e inicia ela com
		 * 2 esteiras, garra e nenhum sensor ativo
		 */
//		Veiculo carro = new Veiculo();
		/*
		 * Cria instancia de carro e inicia ela com
		 * 2 esteiras, garra e sensor de cor ativo
		 */
//		Veiculo carro = new Veiculo(false,true,false);
		/*
		 * Cria instancia de carro e inicia ela com
		 * 2 esteiras, garra e sensor infravermelho ativo
		 */
//		Veiculo carro = new Veiculo(false,false,true);

		/* Define velocidade de movimento em graus por segundo para x graus por segundo (GPS)
		* o motor ira rotacionar as rodas que movimentam a esteira x graus em 1 segundo 
		* e repetir.
		* OBS: este metodo apenas seta a velocidade, ele nao gera movimento
		*/
		carro.setVelocidadeEsteirasGrau(360);
		
		/*
		 * Define velocidade de movimento em rotacoes por segundo (RPS)
		 * 1 rotacao = 360 graus
		 * motor ira rotacionar as rodas que movimento a esteira em x rotacoes por segundo 
		 */
		//carro.setVelocidadeEsteirasRotacao(1);
		
		/* Faz carro andar para frente por t segundos
		 * Ele freia apos isso.
		 * Para andar para frente indefinidamente basta nao colocar
		 * nada como arguento.
		 * Pode-se tambem redefinir a velocidade (em GPS) colocando-a como segundo argumento
		 */
		carro.setEsteirasForward(4);
		
		/* Faz carro andar para tras por t segundos
		 * Ele freia apos isso.
		 * Para andar para frente indefinidamente basta nao colocar
		 * nada como arguento.
		 * Pode-se tambem redefinir a velocidade (em GPS) colocando-a como segundo argumento
		 */
		carro.setEsteirasBackward(4);
		
		/*
		 * Operacoes abaixo fazem o carro curvar por t segundos e parar
		 * ou indefinidamente caso nao passe argumento
		 * ao curvar a velocidade muda para 360 gps e os motores param a sincronizacao 
		 * quando em versao de movimento sem tempo definido
		 */
//		carro.curvaDireita(2);
//		carro.curvaEsquerda(2);
		
		/*
		 * carro toca um sinal sonoro rapidamente
		 * existem 5 opcoes: beep1, beep2, ..., beep5
		 */
//		carro.ev3.beep1();
		
		/*
		 * carro espera t segundos para realizar proximo comando
		 * obs: existe opcao para milissegundos com esperaMilissegundos(t)
		 */
//		carro.ev3.esperaSegundos(2);
//		carro.ev3.beep5();
		
		
		/*
		 * carro anda e para
		 */
//		carro.setEsteirasForward();
//		carro.ev3.esperaMilissegundos(500);
//		carro.stop();
		
		
		/*
		 * Carro abre e fecha garra apos 1 segundo
		 */
		carro.abreGarra();
		carro.ev3.beep2();
		carro.ev3.esperaSegundos(1);
		carro.fechaGarra();
		
		/*
		 * Utiliza Sensores para coletar amostras no vetor de amostras do carro
		 * OBS: sensores devem ter sido ativados
		 */
		carro.coletaAmostras();
		System.out.println("Toque: "+carro.amostras[carro.tq.getOffset()]
				+" Preto e Branco: "+carro.amostras[carro.pb.getOffset()]
				+" Infravermelho: " +carro.amostras[carro.iv.getOffset()]);

		/*
		 * carro anda para tras ate sensor de toque detectar batida
		 */
		carro.recuaAteColidir();
		
		
		/*
		 * carro coleta sinais de controle remoto
		 * cada botao ou combinacao de 2 botoes possui um numero
		 * inteiro respectivo, pode-se programar acoes diferentes 
		 * para cada valor lido.
		 * OBS: sensor infravermelho inicia em modo de receptor de controle
		 * eh possivel mudar para modo detector de distancia, que
		 * mede distancia em cm de algum objeto ou superficie na frente do sensor
		 */
//		carro.coletaAmostras();
//		System.out.println(carro.amostras[carro.iv.getOffset()]);
		
		
		/*
		 * desativa  sensor de toque
		 * OBS: argumentos segue mesma ordem de ativasensores() e construtor de veiculo
		 */
		carro.desativaSensores(true, true, true);
		
		
		/*
		 * liga o sensor de toque, caso tenha sido desligado
		 * ou nao tenha sido ligado no inicio do progama
		 */
		carro.ativaSensores(true, false, false);
		
		/*
		 * carro pisca o led em combinacao de cor e padrao
		 * OBS: 7 combinacoes possiveis: corLed(1), ... , corLed(7);
		 */
		carro.ev3.corLed(4);

		
		
		/*
		 * fecha todas as portas abertas
		 * de sensores e motores
		 */
		carro.fechaPortas();
		
	}

}
