import lejos.utility.Delay;

public class Esteira extends MotorLargo{
	/**
	 * Construtor de Esteira<br>
	 * OBS: por padrao, sincronizacao comeca ativada
	 * @param porta : String <br>
	 * OBS: Apenas "B" ou "C" :
	 */
	public Esteira(String porta) 
	{
		super(porta);
	}
	
	/**
	 * Sincroniza com outra esteira
	 * @param esteira2
	 */
	public void sincronizarCom(Esteira esteira2)
	{
		this.setMotoresSincronizados(esteira2.motorLargo);
	}
	
	
	
	/**
	 * rotaciona motor x angulos e libera processamento apos isso 
	 * @param angulo
	 */
	public void rotacionaAngulo(int angulo)
	{
		//todos os comandos entre start e end sao realizados em sincronia com outros no EV3
		if(sincAtiva) this.motorLargo.startSynchronization();
		this.motorLargo.rotateTo(angulo, true);
		if(sincAtiva) this.motorLargo.endSynchronization();
	}

	
	public void ligaFrente() {
		// TODO Auto-generated method stub
		if(sincAtiva) this.motorLargo.startSynchronization();
		this.motorLargo.forward();
		if(sincAtiva) this.motorLargo.endSynchronization();
	}
	/**
	 * roda gera movimento para a frente por tempo determinado
	 * @param tempo em segundos
	 */
	public void ligaFrente(int tempo) {
		// TODO Auto-generated method stub
		if(sincAtiva) this.motorLargo.startSynchronization();
		this.motorLargo.forward();
		Delay.msDelay(tempo*1000);
		this.motorLargo.stop();
		if(sincAtiva) this.motorLargo.endSynchronization();
	}

	public void ligaTras() {
		if(sincAtiva) this.motorLargo.startSynchronization();
		this.motorLargo.backward();
		if(sincAtiva) this.motorLargo.endSynchronization();
	}
	
	/**
	 * roda gera movimento para tras
	 * @param tempo em segundos
	 */
	public void ligaTras(int tempo) {
		if(sincAtiva) this.motorLargo.startSynchronization();
		this.motorLargo.forward();
		Delay.msDelay(tempo * 1000);
		this.motorLargo.stop();
		if(sincAtiva) this.motorLargo.endSynchronization();
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
		if(sincAtiva) this.motorLargo.startSynchronization();
		this.motorLargo.stop();
		if(sincAtiva) this.motorLargo.endSynchronization();
	}
	/**
	 * Motor para de gerar movimento, continua rodando ate parar
	 */
	public void paraMotor() {
		// TODO Auto-generated method stub
		if(sincAtiva) this.motorLargo.startSynchronization();
		this.motorLargo.flt();
		if(sincAtiva) this.motorLargo.endSynchronization();
	}
	
}
