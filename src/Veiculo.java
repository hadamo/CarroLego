import lejos.hardware.ev3.LocalEV3;
import lejos.utility.Delay;


public class Veiculo {
	public Garra garra;
	public Esteira dir,esq;
	public SensorToque tq;
	public SensorInfraVermelho iv;
	public SensorPretoBranco pb;
	public EV3Cerebro ev3;
	
	/**
	 * Strings para identificar as portas, robô atual segue o padrão abaixo:
	 * Motor esquerdo: B, Motor direito = C
	 * Garra: A
	 * Sensor toque: 4, Sensor InfraRed: 3
	 * Sensor preto e branco: 2
	 * @param portaMotorEsq
	 * @param portaMotorDir
	 * @param portaGarra
	 * @param portaSensorTq
	 * @param portaSensorIR
	 * @param portaSensorPB
	 */
	public Veiculo(String portaMotorEsq, String portaMotorDir, String portaGarra, String portaSensorTq,
			String portaSensorIR,String portaSensorPB )
	{
		garra = new Garra(ev3.getPorta(portaGarra));
		dir = new Esteira(ev3.getPorta(portaMotorDir));
		esq = new Esteira(ev3.getPorta(portaMotorEsq));
		tq = new SensorToque(ev3.getPorta(portaSensorTq));
		iv = new SensorInfraVermelho(ev3.getPorta(portaSensorIR));
		pb = new SensorPretoBranco(ev3.getPorta(portaSensorPB));
		ev3 = new EV3Cerebro();
	}
	/**
	 * Construtor com portas já especificadas.
	 */
	public Veiculo()
	{
		garra = new Garra(ev3.getPorta("A"));
		dir = new Esteira(ev3.getPorta("C"));
		esq = new Esteira(ev3.getPorta("B"));
		tq = new SensorToque(ev3.getPorta("S4"));
		iv = new SensorInfraVermelho(ev3.getPorta("S3"));
		pb = new SensorPretoBranco(ev3.getPorta("S2"));
		ev3 = new EV3Cerebro();
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
	//como a frente do robo agora eh o sensor e nao a garra, os movimentos foram
	//invertidos com relacao a classe tracker
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
	public void curvaDireita()
	{
		this.setVelocidadeEsteiras(360);
		this.esq.ligaTras();
		this.dir.ligaFrente();
	}
	public void curvaEsquerda()
	{
		this.setVelocidadeEsteiras(360);
		this.dir.ligaTras();
		this.esq.ligaFrente();
	}
	public void curvaDireita(int segundos)
	{
		this.setVelocidadeEsteiras(360);
		this.esq.ligaTras();
		this.dir.ligaFrente(segundos);
	}
	public void curvaEsquerda(int segundos)
	{
		this.setVelocidadeEsteiras(360);
		this.dir.ligaTras();
		this.esq.ligaFrente(segundos);
	}

	/*
	 * public void inverteFrente() { this.dir.closeMotor(); this.esq.closeMotor();
	 * this.dir = new Esteira("L"); this.esq = new Esteira("R"); }
	 */
	public void fechaPortas()
	{
		this.dir.closeMotor();
		this.esq.closeMotor();
		this.garra.closeMotor();
		this.tq.closeSensor();
		this.pb.closeSensor();
		this.iv.closeSensor();
	}
	

}
