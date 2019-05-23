import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public abstract class MotorLargo extends Motor{
	public EV3LargeRegulatedMotor motorLargo;
	public MotorLargo(String porta) {
		if(porta == "B") this.motorLargo = new EV3LargeRegulatedMotor(MotorPort.C);
		else if(porta == "C") this.motorLargo = new EV3LargeRegulatedMotor(MotorPort.B);
	}
	
	/**
	 * 
	 */
	public int getTacometro() {
//		this.tacometro = this.roda.motorLargo.getTachoCount();
		return this.motorLargo.getTachoCount();
	}
	
	public void resetTacometro() {
		this.motorLargo.resetTachoCount();
//		this.tacometro = 0;
	}
	
	
	@Override
	public void setVelocidade(float rps)
	{
		if(rps <= this.motorLargo.getMaxSpeed()) 
		{
			this.motorLargo.setSpeed(rps);
			
		}
	}
	
	@Override
	public void setVelocidade(int rps)
	{
		if(rps <= this.motorLargo.getMaxSpeed()) 
		{
			this.motorLargo.setSpeed(rps);
		}
	}
	
	public float getVelocidadeGPS()
	{
		return this.motorLargo.getRotationSpeed();
	}
	
	public int getVelocidadeRPS()
	{
		//testar isso, ver se bate com rpm ou rps
		return this.motorLargo.getSpeed();
	}
	
	@Override
	public float getMaxVeloGPS() {
		// TODO Auto-generated method stub
		return this.motorLargo.getMaxSpeed();
	}
	
	@Override
	public float getMaxVeloRPS() {
		// TODO Auto-generated method stub
		// 1 rps = 6 gps
		return this.motorLargo.getMaxSpeed()*6;
	}
	@Override
	public void closeMotor()
	{
		this.motorLargo.stop();
		this.motorLargo.close();
	}
}
