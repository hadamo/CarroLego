
public class Garra extends MotorMedio{
	/**
	 * angulo necessario para abrir totalmente eh -60 e 60 para fechar totalmente
	 */
	final int anguloAbertura = -60; //Angulo de abertura Total
	final int anguloFechamento = 60; // Angulo de fechamento Total
	private boolean aberta = false;	
	public Garra()
	{
		super();
	}
	
	/** abre garra totalmente
	 * 
	 */
	public void abreTotal() 
	{
//		TODO
		motor.rotate(this.anguloAbertura, true);
		this.aberta = true;
	}
	
	/**
	 * Abre garra no angulo especificado
	 * @param angulo
	 */
	public void abreAng(int angulo)
	{
		this.motor.rotate(angulo, true);
		this.aberta = true;
	}
	
	/**
	 * Fecha garra totalmente
	 */
	public void fechaTotal()
	{
//		TODO
		motor.rotate(this.anguloFechamento, true);
		this.aberta = false;
	}
	
	/**
	 * fecha garra no angulo especificado
	 * @param angulo
	 */
	public void fechaAng(int angulo)
	{
		this.motor.rotateTo(-angulo, true);
		this.aberta = false;
	}
	
	/**
	 * verifica se a garra esta aberta
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
	 * OBS: <b>apenas com as pontas</b>, nao detecta se estiver dentro da garra<br>
	 * Caso esteja, o motor estara como Stalled
	 * @return 
	 */
	public boolean isCheia()
	{
		return this.motor.isStalled();
	}
	


}
