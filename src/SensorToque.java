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

	/**
	 * Reseta o contador de toques
	 */
	public void resetContadorToques()
	{
		this.qtdToques = 0;
	}
	
	/**
	 * Metodo que informa quantas vezes foi detectado toque <br> a cada chamada de coletaAmostra
	 * @return qtdToques : int
	 */
	public int getQtdToques() 
	{
		return this.qtdToques;
	}
	
	@Override
	/**
	 * Metodo que faz a coleta de amostra do sensor de toque
	 * recebe vetor de amostrasRecebidas quando usado com outros sensores<br>
	 * ao mesmo tempo
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return 0 para botao nao pressionado, 1 para botao pressionado.
	 */
	public int coletaAmostra(float[] amostrasRecebidas) {
		this.receptorAmostra = sensor.getTouchMode();
		this.sensor.fetchSample(amostrasRecebidas, this.offset);
		if(amostrasRecebidas[this.offset]>0) this.qtdToques++;
		return (int) amostrasRecebidas[this.offset];
	}
	
	/**
	 * Metodo que verifica se o botao esta atualmente pressionado.<br>
	 * Realiza a coleta de amostra e incrementa o contador de toques caso necessario
	 * Recebe vetor de amostras quando usado com outros sensores
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
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
	
	
	@Override
	public void closeSensor() 
	{
		this.sensor.close();
	}
}
