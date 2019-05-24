import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public abstract class MotorLargo extends Motor{
	public EV3LargeRegulatedMotor motorLargo;
	protected EV3LargeRegulatedMotor[] motorLargoSincronizado;

	public MotorLargo(String porta) {
		if(porta == "B") this.motorLargo = new EV3LargeRegulatedMotor(MotorPort.C);
		else if(porta == "C") this.motorLargo = new EV3LargeRegulatedMotor(MotorPort.B);
		this.motorLargoSincronizado = new EV3LargeRegulatedMotor[1];
	}
	
	/**
	 * Marca outro MOTOR LARGO para operacoes sincronizadas.
	 * @param motor2
	 */
	void setMotoresSincronizados(EV3LargeRegulatedMotor motor2)
	{
		this.motorLargoSincronizado[0] = motor2;
	}
	/**
	 * Para de rodar totalmente em sincronia com outro motor<br>
	 * Apenas um dos motores precisa usar este metodo
	 */
	
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
	
	/**
	 * seta velocidade para este motor e para o motor sincronizado com ele
	 * @return
	 */
	public void setVelocidadeSync(int rps)
	{
		if(rps <= this.motorLargo.getMaxSpeed()) 
		{
			this.motorLargo.startSynchronization();
			this.motorLargo.setSpeed(rps);
			this.motorLargo.endSynchronization();
		}
	}
	
	/**
	 * seta velocidade para este motor e para o motor sincronizado com ele
	 * @return
	 */
	public void setVelocidadeSync(float rps)
	{
		if(rps <= this.motorLargo.getMaxSpeed()) 
		{
			this.motorLargo.startSynchronization();
			this.motorLargo.setSpeed(rps);
			this.motorLargo.endSynchronization();
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
