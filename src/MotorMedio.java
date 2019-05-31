import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public abstract class MotorMedio extends Motor {
	public EV3MediumRegulatedMotor motor;
	public MotorMedio()
	{
		motor = new EV3MediumRegulatedMotor(MotorPort.A);	
	}
	
	@Override
	public void closeMotor()
	{
		this.motor.stop();
		this.motor.close();
	}
	

	@Override
	public void setVelocidadeRps(float rps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelocidadeGps(float rps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaxVeloGPS() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMaxVeloRPS() {
		// TODO Auto-generated method stub
		return 0;
	}
}
