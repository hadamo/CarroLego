import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class Esteira extends MotorLargo{
	/**
	 * Construtor de Esteira
	 * 
	 * @param porta : "B" ou "C"
	 */
	public Esteira(String porta) 
	{
		super(porta);
	}
	
	
	public void frenteAngulo(int angulo)
	{
		// faz roda rotacionar at� angulo e libera processamento.
		this.motorLargo.rotateTo(angulo, true);
	}
//	public void 

	
	public void ligaFrente() {
		// TODO Auto-generated method stub
		this.motorLargo.forward();
	}
	/**
	 * roda gera movimento para a frente por tempo determinado
	 * @param tempo em segundos
	 */
	public void ligaFrente(int tempo) {
		// TODO Auto-generated method stub
		this.motorLargo.forward();
		Delay.msDelay(tempo*1000);
		this.motorLargo.stop();
	}

	public void ligaTras() {
		// TODO Auto-generated method stub
		this.motorLargo.backward();
	}
	
	/**
	 * roda gera movimento para tras
	 * @param tempo em segundos
	 */
	public void ligaTras(int tempo) {
		// TODO Auto-generated method stub
		this.motorLargo.forward();
		Delay.msDelay(tempo*1000);
	}
	
	/**
	 * @return retorna true se este roda estiver gerando movimento 
	 */
	public boolean isAndando() {
		// TODO Auto-generated method stub
		return this.motorLargo.isMoving();
	}
	
	/**
	 * @return retorna true se algo estiver bloqueando o movimento deste roda
	 */
	public boolean isTravado()
	{
		return this.motorLargo.isStalled();
	}

	/**
	 * Para a roda totalmente.
	 */
	public void freia() {
		// TODO Auto-generated method stub
		this.motorLargo.stop();
	}
	/**
	 * Motor para de gerar movimento, continua rodando ate parar
	 */
	public void paraMotor() {
		// TODO Auto-generated method stub
		this.motorLargo.flt();
	}
	
}
