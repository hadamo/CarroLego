import lejos.utility.Delay;


public class Veiculo {
	public Garra garra;
	public Esteira dir,esq;
	public SensorToque tq;
	public SensorInfraVermelho iv;
	public SensorPretoBranco pb;
	public EV3Cerebro ev3;
	public boolean toqueIsAtivo, pbIsAtivo, infravermIsAtivo;
	public float[] amostras;
	
	/**
	 * construtor de veiculo definindo quais sensores <br>
	 * estarao ativos
	 * @param toque ativa sensor de toque
	 * @param pretobranco ativa sensor preto e branco
	 * @param infravermelho ativa sensor infravermelho
	 */
	public Veiculo(boolean toque, boolean pretobranco, boolean infravermelho)
	{
		//garra = new Garra();
		//dir = new Esteira("C");
		//esq = new Esteira("B");
		int numSensoresAtivos=0;
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
			iv = new SensorInfraVermelho(--numSensoresAtivos);
			numSensoresAtivos ++;
		}
		ev3 = new EV3Cerebro();
		amostras = new float[numSensoresAtivos];
	}
	/**
	 * Constroi veiculo e ativa todos os 3 sensores
	 */
	public Veiculo()
	{
		garra = new Garra();
		dir = new Esteira("C");
		esq = new Esteira("B");
		tq = new SensorToque(0);
		pb = new SensorPretoBranco(1);
		iv = new SensorInfraVermelho(2);
		ev3 = new EV3Cerebro();
		amostras = new float[3];
	}
	
	
	/**
	 * seta velocidade de ambas as esteiras para um mesmo valor em rotações por segundo
	 * @param rps
	 */
	public void setVelocidadeEsteiras(int rps)
	{
		this.dir.setVelocidade(rps);
		this.esq.setVelocidade(rps);
	}
	

	/**
	 * faz veiculo andar para frente indefinidamente
	 * a velocidade deve ter sido setada anteriormente
	 * @param segundos
	 */
	public void setEsteirasForward()
	{
		this.dir.ligaFrente();
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
	public void setEsteirasForward(int segundos, int rps)
	{
		this.setVelocidadeEsteiras(rps);
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
	public void setEsteirasBackward(int segundos, int rps)
	{
		this.setVelocidadeEsteiras(rps);
		this.dir.ligaTras();
		this.esq.ligaTras();
		Delay.msDelay(segundos*1000);
		this.stop();
	}
	
	
	/**
	 * para os motores da esteira
	 */
	public void stop()
	{
		this.dir.freia();
		this.esq.freia();
	}
	
	
	/**
	 * anda sempre para direita
	 */
	public void curvaDireita()
	{
		this.setVelocidadeEsteiras(360);
		this.esq.ligaTras();
		this.dir.ligaFrente();
	}
	
	
	/**
	 * anda sempre para esquerda
	 */
	public void curvaEsquerda()
	{
		this.setVelocidadeEsteiras(360);
		this.dir.ligaTras();
		this.esq.ligaFrente();
	}
	
	
	/**
	 * faz curva para direita por certa quantidade de tempo em segundos
	 * @param segundos
	 */
	public void curvaDireita(int segundos)
	{
		this.setVelocidadeEsteiras(360);
		this.esq.ligaTras();
		this.dir.ligaFrente(segundos);
	}
	
	
	/**
	 * faz curva para esquerda por certa quantidade de tempo em segundos
	 * @param segundos
	 */
	public void curvaEsquerda(int segundos)
	{
		this.setVelocidadeEsteiras(360);
		this.dir.ligaTras();
		this.esq.ligaFrente(segundos);
	}
	
	
	/**
	 * veiculo segue linha reta enquanto ler cor preta <br>
	 * com sensor de cor
	 */
	public void segueLinha()
	{
		this.setEsteirasForward();
		while(this.pb.isPreto());
		this.ev3.corLed(7);
		this.ev3.beep2();
		this.stop();
	}
	
	
	/**
	 * veiculo anda para tras ate o sensor<br>
	 * de toque detectar que foi pressionado contra algo
	 */
	public void recuaAteColidir() 
	{
		this.setEsteirasBackward();
		while(!this.tq.isPressionado());
		this.ev3.corLed(5);
		this.ev3.beep1();
		this.stop();
	}
	
	
	/**
	 * coleta amostras dos sensores ativos 
	 * @param toque
	 * @param pretobranco
	 * @param infravermelho
	 */
	public void coletaAmostras(boolean toque, boolean pretobranco, boolean infravermelho)
	{
		if(toque) this.tq.coletaAmostra(this.amostras);
		if(pretobranco) this.tq.coletaAmostra(this.amostras);
		if(infravermelho) this.tq.coletaAmostra(this.amostras);
	}
	
	/**
	 * fecha todas as portas ativas <br>
	 * de motores e sensores <br>
	 * OBS: para fechar um motor ou sensor em especifico<br>
	 * utilize o metodo close proprio dele.
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
		}
		if(pbIsAtivo)
		{
			this.pb.closeSensor();
			this.pbIsAtivo = false;
		}
		if(infravermIsAtivo)
		{ 
			this.iv.closeSensor();
			this.infravermIsAtivo = false;
		}
	}
	

}
