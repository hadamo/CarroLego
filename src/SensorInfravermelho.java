import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;

public class SensorInfravermelho extends Sensor {
	private EV3IRSensor sensor;
	private int canalControle;
	private int comandoRecebido;
	private int comandoPadrao;
	private int modoOperativo;
	/**
	 * construtor padrao de sensor infravermelho<br>
	 * Modo operativo: receptor de controle<br>
	 * Para mudar utilizar comando setModoOperativo(int)
	 * @param offSet
	 */
	public SensorInfravermelho(int offSet)
	{
		super(offSet);
		this.sensor = new EV3IRSensor(SensorPort.S2);
		this.setAmostra(-1);
		this.canalControle = 0;
		this.comandoRecebido = -1;
		this.comandoPadrao = 0;
		this.modoOperativo = 1;
	}
	
	/**
	 * Construtor de sensor infravermelho
	 * @param offSet
	 * @param modoOp  1 = modo receptor de controle <br> 2 = modo detector de distancia
	 */
	public SensorInfravermelho(int offSet, int modoOp)
	{
		super(offSet);
		this.sensor = new EV3IRSensor(SensorPort.S2);
		this.setAmostra(-1);
		this.canalControle = 0;
		this.comandoRecebido = -1;
		this.comandoPadrao = 0;
		this.modoOperativo = modoOp;
	}
	public int getCanalControle()
	{
		return this.canalControle;
	}
	
	/**
	 * Muda modo operativo do sensor infravermelho
	 * @param x = 1 ativa modo controle
	 * @param x = 2 ativa modo distancia
	 */
	public void setModoOperativo(int x)
	{
		this.modoOperativo = x;
	}
	/**
	 * Set canal Padrão para controle remoto, deve ser<br>
	 * valor no intervalo [0,3].<br>
	 * *OBS: No controle os canais estao em [1,4]
	 * @param <INT>
	 */
	public void setCanalControle(int novoCanal)
	{
		if(novoCanal>=0 && novoCanal<4) this.canalControle = novoCanal;
		else System.err.println("Valor Invalido, insira valor x em [0,3]");
	}
	
	/**
	 * Set comando Padrão para controle remoto, deve ser<br>
	 * valor no intervalo [1,11].<br>
	 * Comandos:<br>
	 * 1 TOP-LEFT<br> 2 BOTTOM-LEFT<br>3 TOP-RIGHT<br>4 BOTTOM-RIGHT<br>5 TOP-LEFT + TOP-RIGHT<br>
	 * 6 TOP-LEFT + BOTTOM-RIGHT<br>7 BOTTOM-LEFT + TOP-RIGHT<br>8 BOTTOM-LEFT + BOTTOM-RIGHT<br>
	 * 9 CENTRE/BEACON<br>10 BOTTOM-LEFT + TOP-LEFT<br>11 TOP-RIGHT + BOTTOM-RIGHT
	 * @param <INT>
	 */
	public void setComandoPadrao(int cmd)
	{
		if(cmd>0 && cmd<12) this.comandoPadrao = cmd;
		else System.err.println("Valor Invalido, insira valor x em [1,11]");
	}
	
	public int getComandoPadrao()
	{
		return this.comandoPadrao;
	}


	/**
	 * Metodo que recebe amostras do sensor em 2 modos diferentes.
	 * 1 - Para modo de receptor de controle <br>o comando do CONTROLE REMOTO pelo sensor INFRAVERMELHO,<br>
	 * Apenas para o canal atualmente definido como Padrao.
	 * Guarda comando no vetor de amostras recebidas
	 * Comandos:<br>
	 * 1 TOP-LEFT<br> 2 BOTTOM-LEFT<br>3 TOP-RIGHT<br>4 BOTTOM-RIGHT<br>5 TOP-LEFT + TOP-RIGHT<br>
	 * 6 TOP-LEFT + BOTTOM-RIGHT<br>7 BOTTOM-LEFT + TOP-RIGHT<br>8 BOTTOM-LEFT + BOTTOM-RIGHT<br>
	 * 9 CENTRE/BEACON<br>10 BOTTOM-LEFT + TOP-LEFT<br>11 TOP-RIGHT + BOTTOM-RIGHT<br><br>
	 * 
	 * 2 - Para modo de detector de distancia
	 * @param amostrasRecebidas vetor de amostras para multiplos sensores
	 * @return numero do botao (ou combinacao de botoes) : int
	 */
	public int coletaAmostra(float[] amostrasRecebidas) {
		if(this.modoOperativo == 1)
		{
			this.comandoRecebido = sensor.getRemoteCommand(this.canalControle);
			amostrasRecebidas[this.offset] = this.comandoRecebido;
			return this.comandoRecebido;
		}else
		{
			this.receptorAmostra = sensor.getDistanceMode();
			this.sensor.fetchSample(amostrasRecebidas, this.offset);
			return (int) amostrasRecebidas[this.offset];
		}
	}
	@Override
	/**
	 * 1 - Para modo de receptor de controle <br>o comando do CONTROLE REMOTO pelo sensor INFRAVERMELHO,<br>
	 * Apenas para o canal atualmente definido como Padrao.
	 * Comandos:<br>
	 * 1 TOP-LEFT<br> 2 BOTTOM-LEFT<br>3 TOP-RIGHT<br>4 BOTTOM-RIGHT<br>5 TOP-LEFT + TOP-RIGHT<br>
	 * 6 TOP-LEFT + BOTTOM-RIGHT<br>7 BOTTOM-LEFT + TOP-RIGHT<br>8 BOTTOM-LEFT + BOTTOM-RIGHT<br>
	 * 9 CENTRE/BEACON<br>10 BOTTOM-LEFT + TOP-LEFT<br>11 TOP-RIGHT + BOTTOM-RIGHT <br><br>
	 * 
	 * 2 - Para modo de detector de distancia
	 * @return numero do botao (ou combinacao de botoes) : int modo controle
	 * @return distancia do sensor a algum objeto/supericie em cm : int modo distancia <br>
	 * se retorno for = 2147483647, o alvo esta fora do limite do sensor ( +-50cm)
	 */	
	public int coletaAmostra() {

		if(this.modoOperativo == 1)
		{
			this.comandoRecebido = sensor.getRemoteCommand(this.canalControle);
			return this.comandoRecebido;
		}else
		{
			this.receptorAmostra = sensor.getDistanceMode();
			float []amostras = new float[receptorAmostra.sampleSize()];
			this.sensor.fetchSample(amostras, 0);
			return (int) amostras[0];
		}
	}
	
	public int getComandoControle()
	{
		this.modoOperativo = 1;
		this.comandoRecebido = coletaAmostra();
		return this.comandoRecebido;
	}
	
	public int getDistancia()
	{
		this.modoOperativo = 2;
		return coletaAmostra();
	}
	
	@Override
	public void closeSensor() 
	{
		this.sensor.close();
	}
	
	/*@Override
	public void selecionaModoOperacao(int modo) {
		// TODO Auto-generated method stub
	}*/
}
