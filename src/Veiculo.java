import lejos.utility.Delay;


public class Veiculo {
	public Garra garra;
	public Esteira dir,esq;
	public SensorToque tq;
	public SensorInfraVermelho iv;
	public SensorPretoBranco pb;
	public EV3Cerebro ev3;
	public float[] amostras;
	
	
	public Veiculo()
	{
		garra = new Garra();
		dir = new Esteira("C");
		esq = new Esteira("B");
		tq = new SensorToque(0);
		pb = new SensorPretoBranco(1);
		iv = new SensorInfraVermelho(2);
		ev3 = new EV3Cerebro();
		pb.setReceptor();
		tq.setReceptor();
		amostras = new float[pb.getTamanhoAmostra() + tq.getTamanhoAmostra()];
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
	public void segueLinha()
	{
		this.setEsteirasForward();
		while(this.pb.isPreto());
		this.ev3.corLed(7);
		this.ev3.beep2();
		this.stop();
	}
	
	public void recuaAteColidir() 
	{
		this.setEsteirasBackward();
		while(!this.tq.isPressionado());
		this.ev3.corLed(5);
		this.ev3.beep1();
		this.stop();
	}
	 
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
