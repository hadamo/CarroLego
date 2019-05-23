import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class SensorToque extends Sensor{
	private EV3TouchSensor sensor;
	private int qtdToques;
	
	public SensorToque(int offSet)
	{
		super(offSet);
		this.sensor = new EV3TouchSensor(SensorPort.S4);
		this.qtdToques = 0;
	}

	
	public void resetContadorToques()
	{
		this.qtdToques = 0;
	}
	public int getQtdToques() 
	{
		return this.qtdToques;
	}
	
	/**
	 * Metodo que faz a coleta de amostra do sensor de toque
	 * recebe vetor de amostrasRecebidas quando usado com outros sensores<br>
	 * ao mesmo tempo
	 * @return 0 para botao nao pressionado, 1 para botao pressionado.
	 */
	public int coletaAmostra(float[] amostrasRecebidas) {
		this.receptorAmostra = sensor.getTouchMode();
		this.sensor.fetchSample(amostrasRecebidas, this.offset);
		return (int) amostrasRecebidas[this.offset];
	}
	@Override
	/**
	 * Metodo que faz a coleta de amostra do sensor de toque
	 * @return 0 para botao nao pressionado, 1 para botao pressionado.
	 */
	public int coletaAmostra() {
		this.receptorAmostra = sensor.getTouchMode();
		float []amostras = new float[receptorAmostra.sampleSize()];
		this.sensor.fetchSample(amostras, 0);
		return (int) amostras[0];
	}
	
	/**
	 * Metodo que verifica se o botao esta atualmente pressionado.<br>
	 * Realiza a coleta de amostra e incrementa o contador de toques caso necessario
	 * Recebe vetor de amostras quando usado com outros sensores
	 * @return true para pressionado, false para nao pressionado :boolean
	 */
	public boolean isPressionado(float[] amostrasRecebidas)
	{
		boolean resultado = false;
		if(coletaAmostra(amostrasRecebidas) > 0) 
		{
			resultado = true;
		}	
		return resultado;
	}
	
	/**
	 * Verifica se o botao esta atualmente pressionado
	 * @return true ou false
	 */
	public boolean isPressionado()
	{
		boolean resultado = false;
		if(coletaAmostra() > 0) 
		{
			resultado = true;
		}	
		return resultado;
	}
	
	public int getTamanhoAmostra()
	{
		return this.receptorAmostra.sampleSize();
	}
	
	@Override
	public void closeSensor() 
	{
		this.sensor.close();
	}
}
