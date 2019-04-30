import lejos.hardware.Button;


public class Veiculo {
	public Garra g;
	public Esteira dir,esq;
	public SensorToque st;
	public Veiculo()
	{
		g = new Garra();
		dir = new Esteira("R");
		esq = new Esteira("L");
		st = new SensorToque();
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
	public void setEsteirasBackward()
	{
		this.dir.ligaTras();
		this.esq.ligaTras();

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
		this.g.closeMotor();
	}
	
	
	
	public static void main(String[] args) {
		
		Veiculo carro = new Veiculo();
//		EV3LargeRegulatedMotor motorLargo = new EV3LargeRegulatedMotor(MotorPort.C);
//		motorLargo.setSpeed(360);
		while(Button.ESCAPE.isUp())
		{
//			Button.waitForAnyPress();
//			carro.g.abre();
//			Button.waitForAnyPress();
//			carro.g.fecha();
			carro.setVelocidadeEsteiras(360);
			carro.setEsteirasForward();
//			motorLargo.forward();
		}
		
	}

}
