import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class SensorToque extends Sensor{
	private EV3TouchSensor sensor;
	private int qtdToques;
	private float ultimaAmostra;
	
	public SensorToque(Port porta)
	{
		super(porta);
//		this.sensor = new EV3TouchSensor(SensorPort.S4);
		this.sensor = new EV3TouchSensor(this.porta);
		this.qtdToques = 0;
		this.ultimaAmostra = 0;
	}

	public void setUltimaAmostra(float valor)
	{
		this.ultimaAmostra = valor;
	}
	public float getUltimaAmostra()
	{
		return this.ultimaAmostra;
	}
	public void resetContadorToques()
	{
		this.qtdToques = 0;
	}
	public int getQtdToques() 
	{
		return this.qtdToques;
	}
	@Override
	/**
	 * Metodo que faz a coleta de amostra do sensor de toque
	 * @return 0 para botao nao pressionado, 1 para botao pressionado.
	 */
	public int coletaAmostra() {
		this.receptorAmostra = sensor.getTouchMode();
		this.iniciaVetorAmostras();
		this.sensor.fetchSample(this.getVetorAmostras(), 0);
		return (int) this.getAmostra();
	}
	
	/**
	 * Metodo que verifica se o botao esta atualmente pressionado.<br>
	 * Realiza a coleta de amostra e incrementa o contador de toques caso necessario
	 * @return true para pressionado, false para nao pressionado :boolean
	 */
	public boolean isPressionado()
	{
		boolean resultado = false;
		this.setUltimaAmostra(this.getAmostra());
		if(coletaAmostra() > 1) 
		{
			resultado = true;
		}
		if(this.getAmostra() > this.ultimaAmostra)
		{
			this.qtdToques++;
		}		
		return resultado;
	}
	
	/**
	 * Metodo que retorna por quantos segundos
	 * o botao foi pressionado da ultima vez.
	 * @return tempo em segundos : int
	 */
/*	public int tempoPressionado()
	{
		
	}*/
	
	
	/*@Override
	public void selecionaModoOperacao(int modo) {
		// TODO Auto-generated method stub
	}*/
	
	@Override
	public void closeSensor() 
	{
		this.sensor.close();
	}
}
