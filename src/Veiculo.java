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
	 * serao ativados
	 * @param toque : boolean ativa sensor de toque
	 * @param pretobranco : boolean ativa sensor preto e branco
	 * @param infravermelho : boolean ativa sensor infravermelho
	 */
	public Veiculo(boolean toque, boolean pretobranco, boolean infravermelho)
	{
		garra = new Garra();
		dir = new Esteira("C");
		esq = new Esteira("B");
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
			iv = new SensorInfravermelho(--numSensoresAtivos);
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
		iv = new SensorInfravermelho(2);
		ev3 = new EV3Cerebro();
		amostras = new float[3];
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
		System.out.println(this.dir.getTacometro());
		System.out.println(this.esq.getTacometro());
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
	 * para os motores da esteira
	 */
	public void stop()
	{
		this.esq.freia();
		this.dir.freia();
	}
	
	
	/**
	 * anda sempre para direita
	 */
	public void curvaDireita()
	{
		this.setVelocidadeEsteirasGrau(360);
		this.esq.ligaTras();
		this.dir.ligaFrente();
	}
	
	
	/**
	 * anda sempre para esquerda
	 */
	public void curvaEsquerda()
	{
		this.setVelocidadeEsteirasGrau(360);
		this.dir.ligaTras();
		this.esq.ligaFrente();
	}
	
	
	/**
	 * faz curva para direita por certa quantidade de tempo em segundos
	 * @param segundos
	 */
	public void curvaDireita(int segundos)
	{
		this.setVelocidadeEsteirasGrau(360);
		this.esq.ligaTras();
		this.dir.ligaFrente(segundos);
	}
	
	
	/**
	 * faz curva para esquerda por certa quantidade de tempo em segundos
	 * @param segundos
	 */
	public void curvaEsquerda(int segundos)
	{
		this.setVelocidadeEsteirasGrau(360);
		this.dir.ligaTras();
		this.esq.ligaFrente(segundos);
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
