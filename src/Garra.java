

public class Garra extends MotorMedio{
	/**
	 * angulo necessario para abrir totalmente eh -70 e 70 para fechar totalmente
	 */
	final int anguloAbertura = -75; //Angulo de abertura Total
	final int anguloFechamento = 75; // Angulo de fechamento Total
	private boolean aberta = false;	
	public Garra()
	{
		super();
	}
	
	/** abre garra totalmente
	 * 
	 */
	public void abre() 
	{
//		TODO
		motor.rotateTo(this.anguloAbertura, true);
		this.aberta = true;
	}
	
	/**
	 * Abre garra no angulo especificado
	 * @param angulo
	 */
	public void abre(int angulo)
	{
		this.motor.rotateTo(angulo, true);
		this.aberta = true;
	}
	
	/**
	 * Fecha garra totalmente
	 */
	public void fecha()
	{
//		TODO
		motor.rotateTo(this.anguloFechamento, true);
		this.aberta = false;
	}
	
	/**
	 * fecha garra no angulo especificado
	 * @param angulo
	 */
	public void fecha(int angulo)
	{
		this.motor.rotateTo(-angulo, true);
		this.aberta = false;
	}
	
	/**
	 * verifica se a garra encontra-se aberta
	 * @return
	 */
	public boolean isAberta()
	{
		return this.aberta;
	}
	
	public boolean isFechada()
	{
		return !this.aberta;
	}
	
	/**
	 * Verifica se a garra esta apertando algo com as pontas.<br>
	 * Caso esteja, o motor esta como Stalled
	 * Precisa testar
	 * @return 
	 */
	public boolean isApertando()
	{
		return this.motor.isStalled();
	}
	


}
