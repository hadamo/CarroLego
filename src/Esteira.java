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

	/**
	 * Sincroniza com outra esteira
	 * @param esteira2
	 */
	public void sincronizarCom(Esteira esteira2)
	{
		this.setMotoresSincronizados(esteira2.motorLargo);
	}
	
	/**
	 * anda para frende indefinidamente
	 * @param sincronizado true ou false para realizar acao em sincroniza com outra esteira
	 */
	public void ligaFrente(boolean sincronizado) {
		// TODO Auto-generated method stub
		if(sincronizado) this.motorLargo.startSynchronization();
		this.motorLargo.forward();
		if(sincronizado) this.motorLargo.endSynchronization();
	}
	/**
	 * roda gera movimento para a frente por tempo determinado
	 * @param tempo em segundos
	 * @param sincronizado true ou false para realizar acao em sincroniza com outra esteira
	 */
	public void ligaFrente(int tempo, boolean sincronizado) {
		// TODO Auto-generated method stub
		this.motorLargo.forward();
		Delay.msDelay(tempo*1000);
		this.motorLargo.stop();
	}
	
	/**
	 * anda para tras indefinidamente
	 * @param sincronizado true ou false para realizar acao em sincroniza com outra esteira
	 */
	public void ligaTras( boolean sincronizado) {
		// TODO Auto-generated method stub
		this.motorLargo.backward();
	}
	
	/**
	 * roda gera movimento para tras
	 * @param tempo em segundos
	 * @param sincronizado true ou false para realizar acao em sincroniza com outra esteira
	 */
	public void ligaTras(int tempo, boolean sincronizado) {
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
	 * Para de rodar totalmente.
	 * @param sincronizado true ou false para realizar acao em sincroniza com outra esteira
	 */
	public void freia(boolean sincronizado) {
		// TODO Auto-generated method stub
		if(sincronizado) this.motorLargo.startSynchronization();
		this.motorLargo.stop();
		if(sincronizado) this.motorLargo.endSynchronization();
	}
	/**
	 * Motor para de gerar movimento, continua rodando ate parar
	 * @param sincronizado true ou false para realizar acao em sincroniza com outra esteira
	 */
	public void paraMotor(boolean sincronizado) {
		// TODO Auto-generated method stub
		if(sincronizado) this.motorLargo.startSynchronization();
		this.motorLargo.flt();
		if(sincronizado) this.motorLargo.endSynchronization();
	}

	
}
