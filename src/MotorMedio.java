import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

public abstract class MotorMedio extends Motor {
	public EV3MediumRegulatedMotor motor;
	public MotorMedio(Port porta)
	{
		super(porta);
//		motor = new EV3MediumRegulatedMotor(MotorPort.A);	
		motor = new EV3MediumRegulatedMotor(this.porta);	
	}
	
	@Override
	public void closeMotor()
	{
		this.motor.stop();
		this.motor.close();
	}
	

	@Override
	public void setVelocidade(int rps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelocidade(float rps) {
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
