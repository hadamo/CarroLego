import lejos.utility.Delay;


public class Veiculo {
	public Garra garra;
	public Esteira dir,esq;
	public SensorToque tq;
	public SensorInfravermelho iv;
	public SensorPretoBranco pb;
	public EV3Cerebro ev3;
	public boolean toqueIsAtivo, pbIsAtivo, infravermIsAtivo;
	private int numSensoresAtivos = 0;
	public float[] amostras;
	public static int delayEntreMotores = 100;
	
	/**
	 * construtor de veiculo definindo quais sensores <br>
	 * serao ativados.
	 * liga sincronizacao entre esteiras
	 * @param toque : boolean ativa sensor de toque
	 * @param pretobranco : boolean ativa sensor preto e branco
	 * @param infravermelho : boolean ativa sensor infravermelho
	 */
	public Veiculo(boolean toque, boolean pretobranco, boolean infravermelho)
	{
		garra = new Garra();
		dir = new Esteira("C");
		esq = new Esteira("B");
		//ativa sincronizacao de motores
		dir.sincronizarCom(esq);
		esq.sincronizarCom(dir);
		if(toque) 
		{
			toqueIsAtivo = true;
			tq = new SensorToque(numSensoresAtivos);
			numSensoresAtivos ++;
		}
		if(pretobranco)
		{
			pbIsAtivo = true;
			pb = new SensorPretoBranco(numSensoresAtivos);
			numSensoresAtivos ++;
		}
		if(infravermelho) 
		{
			infravermIsAtivo = true;
			iv = new SensorInfravermelho(numSensoresAtivos);
			numSensoresAtivos ++;
		}
		ev3 = new EV3Cerebro();
		amostras = new float[numSensoresAtivos];
	}
	/**
	 * Constroi veiculo e ativa todos os 3 sensores
	 * ativa sincronizacao de esteiras
	 */
	public Veiculo()
	{
		garra = new Garra();
		dir = new Esteira("C");
		esq = new Esteira("B");
		//ativa sincronizacao de motores
		dir.sincronizarCom(esq);
		esq.sincronizarCom(dir);
		ev3 = new EV3Cerebro();
	}
	
	
	///////////////// Sincronizacao ////////////////////
	/**
	 * ativa sincronização de de esteiras
	 */
	public void ligaSincronizacaoEsteiras()
	{
		this.dir.setSincronizacaoOn();
		this.esq.setSincronizacaoOn();
	}
	
	/**
	 * desativa sincronização de esteiras
	 */
	public void desligaSincronizacaoEsteiras()
	{
		this.dir.setSincronizacaoOff();
		this.esq.setSincronizacaoOff();
	}
	
	/////////////////////////////////////////////////
	///////////////// Movimento ////////////////////
	///////////////////////////////////////////////


	/**
	 * para os motores da esteira
	 */
	public void stop()
	{
		this.esq.freia();
		this.dir.freia();
	}

	/**
	 * seta velocidade de ambas as esteiras para um mesmo valor em rotações por segundo
	 * @param rps
	 */
	public void setVelocidadeEsteirasRotacao(float rps)
	{
		this.dir.setVelocidadeRps(rps);
		this.esq.setVelocidadeRps(rps);
	}
	
	/**
	 * seta velocidade de ambas as esteiras para um mesmo valor em rotações por segundo
	 * @param rps
	 */
	public void setVelocidadeEsteirasGrau(float gps)
	{
		this.dir.setVelocidadeGps(gps);
		this.esq.setVelocidadeGps(gps);
	}
	

	/**
	 * faz veiculo andar para frente indefinidamente
	 * a velocidade deve ter sido setada anteriormente
	 * @param segundos
	 */
	public void setEsteirasForward()
	{
		this.dir.ligaFrente();
		ev3.esperaMilissegundos(delayEntreMotores);
		this.esq.ligaFrente();		
	}
	
	
	/**
	 * faz veiculo andar para frente por x segundos e depois parar
	 * a velocidade deve ter sido setada anteriormente
	 * @param segundos
	 */
	public void setEsteirasForward(int segundos)
	{
		this.dir.ligaFrente();
		this.esq.ligaFrente();	
		Delay.msDelay(segundos*1000);
		this.stop();
	}
	
	
	/**
	 * faz veiculo andar para frente por x segundos e depois parar
	 * a velocidade deve ser definida em rps
	 * @param segundos
	 * @param rps
	 */
	public void setEsteirasForward(int segundos, float rps)
	{
		this.setVelocidadeEsteirasRotacao(rps);
		this.dir.ligaFrente();
		this.esq.ligaFrente();	
		Delay.msDelay(segundos*1000);
		this.stop();
	}
	
	
	/**
	 * faz veiculo andar para tras indefinidamente
	 * a velocidade deve ter sido setada anteriormente
	 * @param segundos
	 */
	public void setEsteirasBackward()
	{
		this.dir.ligaTras();
		this.esq.ligaTras();
	}
	
	
	/**
	 * faz veiculo andar para tras por x segundos e depois parar
	 * a velocidade deve ter sido setada anteriormente
	 * @param segundos
	 */
	public void setEsteirasBackward(int segundos)
	{
		this.dir.ligaTras();
		this.esq.ligaTras();
		Delay.msDelay(segundos*1000);
		this.stop();
	}
	
	
	/**
	 * faz veiculo andar para tras por x segundos e depois parar
	 * a velocidade deve ser definida em rps
	 * @param segundos
	 * @param rps
	 */
	public void setEsteirasBackward(int segundos, float rps)
	{
		this.setVelocidadeEsteirasRotacao(rps);
		this.dir.ligaTras();
		this.ev3.esperaMilissegundos(delayEntreMotores);
		this.esq.ligaTras();
		Delay.msDelay(segundos*1000);
		this.stop();
	}
	
	
	
	/**
	 * anda sempre para direita<br>
	 * A sincronizacao entre motores eh desativada.<br>
	 * Para reativar use ligaSincronizacaoEsteiras()
	 */
	public void curvaDireita()
	{
		this.setVelocidadeEsteirasGrau(360);
		this.desligaSincronizacaoEsteiras();
		this.esq.ligaTras();
		this.dir.ligaFrente();
	}
	
	/**
	 * anda sempre para esquerda<br>
	 * A sincronizacao entre motores eh desativada.<br>
	 * Para reativar use ligaSincronizacaoEsteiras()
	 */
	public void curvaEsquerda()
	{
		this.setVelocidadeEsteirasGrau(360);
		this.desligaSincronizacaoEsteiras();
		this.dir.ligaTras();
		this.esq.ligaFrente();
	}
	
	
	/**
	 * faz curva para direita por certa quantidade de tempo em segundos<br>
	 * a sincronizacao entre motores eh desativada enquanto curva e reativada apos terminar
	 * @param segundos
	 */
	public void curvaDireita(int segundos)
	{
		this.setVelocidadeEsteirasGrau(360);
		this.desligaSincronizacaoEsteiras();
		this.esq.ligaTras(segundos);
		this.dir.ligaFrente(segundos);
		this.ligaSincronizacaoEsteiras();
	}
	
	
	/**
	 * faz curva para esquerda por certa quantidade de tempo em segundos<br>
	 * a sincronizacao entre motores eh desativada enquanto curva e reativada apos terminar
	 * @param segundos
	 */
	public void curvaEsquerda(int segundos)
	{
		this.setVelocidadeEsteirasGrau(360);
		this.desligaSincronizacaoEsteiras();
		this.dir.ligaTras(segundos);
		this.esq.ligaFrente(segundos);
		this.ligaSincronizacaoEsteiras();
	}
	
/////////////////////////////////////////////////
///////////////// Uso de Sensores //////////////
///////////////////////////////////////////////
	
	/**
	 * Ativa sensores que nao foram ativos quando instancia de veiculo for iniciada<br>
	 * ou sensores que tiveram suas portas fechadas
	 * @param toque : boolean ativa sensor de toque
	 * @param pretobranco : boolean ativa sensor preto e branco
	 * @param infraverm : boolean ativa sensor infravermelho
	 */
	public void ativaSensores(boolean toque, boolean pretobranco, boolean infraverm)
	{
		if(toque && !toqueIsAtivo)
		{
			toqueIsAtivo = true;
			if(this.tq.getOffset() < 0) //caso sensor nao tenha sido ativado nenhuma vez nesta execucao
			{
				this.tq = new SensorToque(numSensoresAtivos);
			}else 
			{
				int offset = this.tq.getOffset();
				this.tq = new SensorToque(offset);
			}
			this.numSensoresAtivos++;
		}
		if(pretobranco && !pbIsAtivo)
		{
			pbIsAtivo = true;
			if(this.pb.getOffset() < 0) //caso sensor nao tenha sido ativado nenhuma vez nesta execucao
			{
				this.pb = new SensorPretoBranco(numSensoresAtivos);
			}else 
			{
				int offset = this.pb.getOffset();
				this.pb = new SensorPretoBranco(offset);
			}
			this.numSensoresAtivos++;
		}
		if(infraverm && !infravermIsAtivo)
		{
			infravermIsAtivo = true;
			if(this.iv.getOffset() < 0) //caso sensor nao tenha sido ativado nenhuma vez nesta execucao
			{
				this.iv = new SensorInfravermelho(numSensoresAtivos);
			}else 
			{
				int offset = this.iv.getOffset();
				this.iv = new SensorInfravermelho(offset);
			}
			this.numSensoresAtivos++;
		}
		this.amostras = new float[numSensoresAtivos];

	}
	
	/**
	 * Desativa sensores que fora ativos quando instancia de veiculo for iniciada<br>
	 * ou sensores que foram abertos posteriormente <br>
	 * OBS: apenas a porta foi fechada, os objetos nao fora destruidos <br>
	 * O offset do sensor nao mudara quando desativado <br>
	 * @param toque : boolean desativa sensor de toque
	 * @param pretobranco : boolean desativa sensor preto e branco
	 * @param infraverm : boolean desativa sensor infravermelho
	 */
	public void desativaSensores(boolean toque, boolean pretobranco, boolean infraverm)
	{
		if(toque && toqueIsAtivo)
		{
			toqueIsAtivo = false;
			this.numSensoresAtivos--;
			this.tq.closeSensor();
		}
		if(pretobranco && pbIsAtivo)
		{
			pbIsAtivo = false;
			this.numSensoresAtivos--;
			this.pb.closeSensor();
		}
		if(infraverm && infravermIsAtivo)
		{
			infravermIsAtivo = false;
			this.numSensoresAtivos--;
			this.iv.closeSensor();
		}
		this.amostras = new float[numSensoresAtivos];
	}
	
	/**
	 * veiculo segue linha reta enquanto nao detectar algo em sua frente <br>
	 * com sensor infravermelho em modo detector de distancia
	 */
	public void forwardEnqtLivre()
	{
		if(infravermIsAtivo && this.iv.getModoOperativo() > 0)
		{
			this.setEsteirasForward();
			while(this.iv.getDistancia(this.amostras) >= 18);
			this.ev3.corLed(2);
			this.ev3.beep4();
			this.stop();
		}
	}
	
	/**
	 * veiculo segue linha reta enquanto ler cor preta <br>
	 * com sensor de cor
	 */
	public void segueLinha()
	{
		if(pbIsAtivo)
		{
			this.setEsteirasForward();
			while(this.pb.isPreto(this.amostras));
			this.ev3.corLed(7);
			this.ev3.beep2();
			this.stop();
		}
	}
	
	
	/**
	 * veiculo anda para tras ate o sensor<br>
	 * de toque detectar que foi pressionado contra algo
	 */
	public void recuaAteColidir() 
	{
		if(toqueIsAtivo)
		{
			this.setEsteirasBackward();
			while(!this.tq.isPressionado(this.amostras));
			this.ev3.corLed(5);
			this.ev3.beep1();
			this.stop();
		}
	}
	
	
	/**
	 * coleta amostras de todos os <b>sensores ativos</b>
	 * OBS1: os dados serao salvos no vetor de amostras do veiculo <br>
	 * OBS2: pode-se obter dados filtrados utilizando metodos <br>
	 * de coleta especificos de cada sensor, como: <br>
	 * <p> getDistancia(amostras) </p>
	 * <p> isBranco(amostras) </p>
	 * <p> isPressionado(amostras) </p>
	 * @param toque
	 * @param pretobranco
	 * @param infravermelho
	 */
	public void coletaAmostras()
	{
		if(toqueIsAtivo) this.tq.coletaAmostra(this.amostras);
		if(pbIsAtivo) this.pb.coletaAmostra(this.amostras);
		if(infravermIsAtivo) this.iv.coletaAmostra(this.amostras);
	}
	
/////////////////////////////////////////////////
//////////////////// Garra  ////////////////////
///////////////////////////////////////////////	
	/**
	 * abre a garra totalmente<br>
	 * OBS: rotacao eh relativa a posicao atual do motor
	 */
	public void abreGarra()
	{
		this.garra.abreTotal();
	}
	
	/**
	 * abre a garra em angulo especificado<br>
	 * OBS: rotacao eh relativa a posicao atual do motor
	 * @param angulo : int
	 */
	public void abreGarra(int angulo)
	{
		this.garra.abreAng(angulo);
	}
	
	/**
	 * fecha garra totalmente<br>
	 * OBS: rotacao eh relativa a posicao atual do motor
	 */
	public void fechaGarra()
	{
		this.garra.fechaTotal();
	}
	
	/**
	 * fecha garra em angulo especificado<br>
	 * OBS: rotacao eh relativa a posicao atual do motor
	 * @param angulo : int
	 */
	public void fechaGarra(int angulo)
	{
		this.garra.fechaAng(angulo);
	}
	
	
/////////////////////////////////////////////////
///////////// Gerencia de Portas  //////////////
///////////////////////////////////////////////
	
	/**
	 * fecha todas as portas ativas <br>
	 * de motores e sensores <br>
	 * OBS: para fechar um motor ou sensor em especifico<br>
	 * Utilize o seu metodo close.<br>
	 * Para reabrir um motor ou sensor, instancie-o novamente.
	 */
	public void fechaPortas()
	{
		this.dir.closeMotor();
		this.esq.closeMotor();
		this.garra.closeMotor();
		if(toqueIsAtivo)
		{
			this.tq.closeSensor();
			this.toqueIsAtivo = false;
			this.numSensoresAtivos--;
		}
		if(pbIsAtivo)
		{
			this.pb.closeSensor();
			this.pbIsAtivo = false;
			this.numSensoresAtivos--;
		}
		if(infravermIsAtivo)
		{ 
			this.iv.closeSensor();
			this.infravermIsAtivo = false;
			this.numSensoresAtivos--;
		}
	}
	
}
