import lejos.hardware.port.Port;

public abstract class Motor {
	public final int VELOCIDADEPADRAO_RPS = 360;// rps
	public final int TEMPOPADRAO = 1000; //milisegundos
	public final double GRAU_POR_CM = 25.7142857; 
	public final double CM_POR_GRAU = 0.03888889;
	public final int DIAMETRO_RODA_CM = 3;// cm (roda que est� ligada ao motor e transmite a for�a pra esteira?)
	public final int COMPRIMENTO_ESTEIRA_CM = 30; //cm
	public Port porta;
//	public final boolean FRENTE = true;
//	public final boolean TRAS = false;
	public Motor(Port porta)
	{
		this.porta = porta;
	}
	
	
	/**
	 * Define velocidade em rotacoes por segundo<br>
	 * *OBS: velocidade max em superficie plana eh 100 * voltagem atual da bateria
	 * @param rps : int
	 */
	public abstract void setVelocidade(int rps);
	
	/**
	 * Define velocidade em rotacoes por segundo<br>
	 * *OBS: velocidade max em superficie plana eh 100 * voltagem atual da bateria
	 * @param rps : float
	 */
	public abstract void setVelocidade(float rps);
	
	/**
	 * @return velocidade maxima com a bateria atual em graus por segundo (GPS)
	 */
	public abstract float getMaxVeloGPS();
	
	/**
	 * @return velocidade maxima com a bateria atual em rotacoes por segundo (RPS)
	 */
	public abstract float getMaxVeloRPS();
	
	/**
	 * Anda para frente ate receber outro comando
	 */
	public abstract void closeMotor();
	
}
