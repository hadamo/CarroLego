import lejos.robotics.SampleProvider;

public abstract class Sensor {
	private float amostrasRecebidas[];
	private int tamanhoAmostra;
	private float amostra;
	public SampleProvider receptorAmostra;

	/**
	 * Construtor de classe abstrata Sensor<br>
	 * Apenas inicializa amostra e tamanhoAmostra com 0<br>
	 * amostrasRecebidas e receptorAmostra com null
	 */
	public Sensor()
	{
		this.amostra = 0;
		this.amostrasRecebidas = null;
		this.tamanhoAmostra = 0;
		this.receptorAmostra = null;
	}

	/**
	 * Atualiza tamanho do vetor de amostras para o SENSOR e MODO DE OPERACAO atual
	 */
	public  void setTamanhoAmostra() 
	{
		this.tamanhoAmostra = receptorAmostra.sampleSize();
	}
	
	/**
	 * Verifica tamanho atual do vetor de amostras
	 * @return tamanhoAmostra : int 
	 */
	public int getTamanhoAmostra()
	{
		return this.tamanhoAmostra;
	}
	
	/**
	 * Verifica vetor de amostras recebidas
	 * @return vetor de amostras recebidas : float[]
	 */
	public float[] getVetorAmostras() 
	{
		if(this.amostrasRecebidas.length > 0) 
			System.err.println("Atualmente nao existem amostras guardadas, tente amostra");
		return this.amostrasRecebidas;
	}
	
	/**
	 * Inicia vetor de amostras recebidas com tamanho de amostra esperada<br>
	 * para o SENSOR e MODO DE OPERACAO atuais
	 */
	public void iniciaVetorAmostras() 
	{
		this.setTamanhoAmostra();
		this.amostrasRecebidas = new float[tamanhoAmostra];
	}
	
	/**
	 * Verifica e guarda ultima atualização do vetor de amostras.
	 * @return caso valido, retorna amostra no canal 1 
	 * @return caso contrário, retorna -1
	 */
	public float getAmostra()
	{
		try {
			this.amostra = this.amostrasRecebidas[0];
			return this.amostra;
		} catch (Exception e) {
			System.err.println("Canal inserido extrapola o permitido para este SENSOR e MODO DE OPERACAO");
			return -1;
		}
	}
	
	/**
	 * Verifica e guarda ultima atualização do vetor de amostras.
	 * @param canal: indice no vetor de amostras
	 * @return caso valido, retorna amostra no canal especificado como argumento 
	 * @return caso contrário, retorna -1
	 */
	public float getAmostra(int canal)
	{
		try {
			this.amostra = this.amostrasRecebidas[canal];
			return this.amostra;
		} catch (Exception e) {
			System.err.println("Canal inserido extrapola o permitido");
			return -1;
		}
	}
	
	public void setAmostra(float valor)
	{
		this.amostra = valor;
	}
	
	/**
	 * Metodo que faz a coleta de amostra, de acordo com SENSOR e MODO DE OPERACAO
	 * @return amostra unica ou multiplas amostras de acordo com SENSOR e MODO DE OPERACAO
	 */
	public abstract int coletaAmostra();
	
}
