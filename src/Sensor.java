
import lejos.robotics.SampleProvider;

public abstract class Sensor {
	protected int amostra;
	protected int offset = -1;
	protected int sizeAmostra;
	public SampleProvider receptorAmostra;

	/**
	 * Construtor de classe abstrata Sensor<br>
	 * Apenas inicializa amostra e tamanhoAmostra com 0<br>
	 * receptorAmostra com null
	 * offset com a posição que a amostra coletada <br>
	 * pelo sensor vai estar no vetor de amostras.
	 */
	public Sensor(int offset)
	{
		this.amostra = 0;
		this.offset = offset;
		this.receptorAmostra = null;
	}

	public int getOffset()
	{
		return this.offset;
	}
	
	public void changeOffset(int newOffset)
	{
		this.offset = newOffset;
	}
	
	public void setAmostra(int valor)
	{
		this.amostra = valor;
	}
	
	public int getUltimaAmostra()
	{
		return this.amostra;
	}
	/**
	 * Metodo que faz a coleta de amostra, de acordo com SENSOR e MODO DE OPERACAO
	 * @return amostra unica ou multiplas amostras de acordo com SENSOR e MODO DE OPERACAO
	 */
	public abstract int coletaAmostra(float[] amostrasRecebidas);
	public abstract void closeSensor();
}
