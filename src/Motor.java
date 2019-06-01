
public abstract class Motor {
	public final int VELOCIDADE_PADRAO_RPS = 360;// rps
	public final int TEMPO_PADRAO_MS = 1000; //milisegundos
	public final double GRAU_POR_CM = 25.7142857; 
	public final double CM_POR_GRAU = 0.03888889;
	public final float RPS_TO_GPS = 360;
	public final int DIAMETRO_RODA_CM = 3;// cm (roda que está ligada ao motor e transmite a força pra esteira?)
	public final int COMPRIMENTO_ESTEIRA_CM = 30; //cm

	
	/**
	 * Define velocidade em rotacao por segundo<br>
	 * O motor vai rotacionar @param vezes em 1 segundo e repetir <br>
	 * cada rotacao = 360 graus
	 * *OBS1: velocidade max em superficie plana eh 100 * voltagem atual da bateria
	 * *OBS2: eh feita conversao para grau por segundo dentro do metodo
	 * *OBS3: apenas define velocidade, nao inicia movimento
	 * @param gps : float graus por segundo
	 */
	public abstract void setVelocidadeRps(float rps);
	
	/**
	 * Define velocidade em grau por segundo<br>
	 * Motor vai rotacionar x graus em 1 segundo e repetir
	 * *OBS: velocidade max em superficie plana eh 100 * voltagem atual da bateria
	 * *OBS3: apenas define velocidade, nao inicia movimento
	 * @param gps : float graus por segundo
	 */
	public abstract void setVelocidadeGps(float gps);
	
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
