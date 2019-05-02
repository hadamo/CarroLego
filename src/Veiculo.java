import lejos.utility.Delay;


public class Veiculo {
	public Garra garra;
	public Esteira dir,esq;
	public SensorToque st;
	public EV3Cerebro ev3;
	public Veiculo()
	{
		garra = new Garra();
		dir = new Esteira("R");
		esq = new Esteira("L");
		st = new SensorToque();
		ev3 = new EV3Cerebro();
	}
	
	public void setVelocidadeEsteiras(int rps)
	{
		this.dir.setVelocidade(rps);
		this.esq.setVelocidade(rps);
	}
	//como a frente do robo agora eh o sensor e nao a garra, os movimentos foram
	//invertidos com relacao a classe tracker 
	public void setEsteirasForward()
	{
		this.dir.ligaFrente();
		this.esq.ligaFrente();		
	}
	public void setEsteirasForward(int segundos)
	{
		this.dir.ligaFrente();
		this.esq.ligaFrente();	
		Delay.msDelay(segundos*1000);
		this.stop();
	}
	
	public void setEsteirasBackward()
	{
		this.dir.ligaTras();
		this.esq.ligaTras();
	}
	
	public void setEsteirasBackward(int segundos)
	{
		this.dir.ligaTras();
		this.esq.ligaTras();
		Delay.msDelay(segundos*1000);
		this.stop();
	}
	
	public void stop()
	{
		this.dir.freia();
		this.esq.freia();
	}
	public void curvaDireita()
	{
		this.dir.setVelocidade(-50);
		this.esq.setVelocidade(50);
		this.setEsteirasForward();
	}
	public void curvaEsquerda()
	{
		this.dir.setVelocidade(50);
		this.esq.setVelocidade(-50);
		this.setEsteirasForward();
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
	}
	

}
