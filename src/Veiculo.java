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
	
	public void setMotorsEqualPower(int power)
	{
		this.dir.setVelocidade(power);
		this.esq.setVelocidade(power);
	}
	//como a frente do robo agora eh o sensor e nao a garra, os movimentos foram
	//invertidos com relacao a classe tracker 
	public void setMotorsForward()
	{
		this.dir.ligaFrente();
		this.esq.ligaFrente();		
	}
	public void setMotorsBackward()
	{
		this.dir.ligaTras();
		this.esq.ligaTras();

	}
	public void stop()
	{
		this.dir.freia();
		this.esq.freia();
//		this.helice.stop();
	}
//	public void turnRight()
//	{
//		this.dir.setVelocidade(-50);
//		this.esq.setVelocidade(50);
//		//this.setMotorsForward();
//	}
	public void turnLeft()
	{
		this.dir.setVelocidade(50);
		this.esq.setVelocidade(-50);
		this.setMotorsForward();
	}

	/*
	 * public void inverteFrente() { this.dir.closeMotor(); this.esq.closeMotor();
	 * this.dir = new Esteira("L"); this.esq = new Esteira("R"); }
	 */
	public void trackerOff()
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
			carro.setMotorsEqualPower(360);
			carro.setMotorsForward();
//			motorLargo.forward();
		}
		
	}

}
