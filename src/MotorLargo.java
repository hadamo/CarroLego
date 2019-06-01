import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public abstract class MotorLargo extends Motor{
	public EV3LargeRegulatedMotor motorLargo;
	protected RegulatedMotor[] motorSincronizado;
	protected boolean sincAtiva;
	
	/**
	 * construtor de motor regulado largo<br>
	 * por padrao sincronizacao comeca ativada
	 * @param porta
	 */
	public MotorLargo(String porta) {
		if(porta == "B") this.motorLargo = new EV3LargeRegulatedMotor(MotorPort.C);
		else if(porta == "C") this.motorLargo = new EV3LargeRegulatedMotor(MotorPort.B);
		this.motorSincronizado = new EV3LargeRegulatedMotor[1];
		sincAtiva = true;

	}
	
	/**
	 * Marca outro MOTOR regulado para operacoes sincronizadas.
	 * @param motor2
	 */
	void setMotoresSincronizados(RegulatedMotor motor2)
	{
		this.motorSincronizado[0] = motor2;
	}
	
	/**
	 * liga sincronizacao de motores <br>
	 * motores sincronizados realizarao suas tarefas em sincronia :o
	 */
	void setSincronizacaoOn()
	{
		this.sincAtiva = true;
	}
	
	/**
	 * desliga sincronizacao de motores
	 */
	void setSincronizacaoOff()
	{
		this.sincAtiva = false;
	}
	
	public int getTacometro() {
//		this.tacometro = this.roda.motorLargo.getTachoCount();
		return this.motorLargo.getTachoCount();
	}
	
	public void resetTacometro() {
		this.motorLargo.resetTachoCount();
	}
		
	@Override
	public void setVelocidadeGps(float gps)
	{
		if(gps <= this.motorLargo.getMaxSpeed()) 
		{
			if(sincAtiva) this.motorLargo.startSynchronization();
			this.motorLargo.setSpeed(gps);
			if(sincAtiva) this.motorLargo.endSynchronization();
		}
	}
	
	@Override
	public void setVelocidadeRps(float rps)
	{
		if(rps <= this.motorLargo.getMaxSpeed()) 
		{
			if(sincAtiva) this.motorLargo.startSynchronization();
			this.motorLargo.setSpeed(rps * RPS_TO_GPS);
			if(sincAtiva) this.motorLargo.endSynchronization();
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
		return this.motorLargo.getMaxSpeed()/RPS_TO_GPS;
	}
	@Override
	public void closeMotor()
	{
		this.motorLargo.stop();
		this.motorLargo.close();
	}
}
