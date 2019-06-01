import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;

public class SensorInfravermelho extends Sensor {
	private EV3IRSensor sensor;
	private int canalControle;
	private int comandoRecebido;
	private int comandoPadrao;
	private int modoOperativo;
	public int distanciaPadraocm = 20;
	/**
	 * construtor padrao de sensor infravermelho<br>
	 * Modo operativo padrao: receptor de controle<br>
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
		this.modoOperativo = 0;
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

	
	////////////////////////////////////////
	//////////// Modo de Operacao /////////
	///////////////////////////////////////
	
	/**
	 * Muda modo operativo do sensor infravermelho
	 * @param x = 1 ativa modo controle
	 * @param x = 2 ativa modo distancia
	 */
	public void setModoOperativo(int x)
	{
		this.modoOperativo = x;
	}
	
	
	public int getModoOperativo()
	{
		return this.modoOperativo;
	}
	
	

	////////////////////////////////////////
	////// Configuracao Modo Controle //////
	///////////////////////////////////////
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
	 * verifica o canal atual para receber comando de controle remoto
	 * @return
	 */
	public int getCanalControle()
	{
		return this.canalControle;
	}
	
	
	/**
	 * Set comando Padrão para controle remoto, deve ser<br>
	 * valor no intervalo [1,11].<br>
	 * Comandos:<br>
	 * 0 nenhum comando ou comando nao reconhecido<br>
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

	

	////////////////////////////////////////
	//////////// Amostragem  ///////////////
	///////////////////////////////////////
	@Override 
	/**
	 * Metodo que recebe amostras do sensor em 2 modos diferentes.
	 * Modo de operacao eh mantido em atributo e pode ser alternado com <br> metodo setModoOperativo(int)
	 * 1 - Para modo de receptor de controle <br>o comando do CONTROLE REMOTO pelo sensor INFRAVERMELHO,<br>
	 * Apenas para o canal atualmente definido como Padrao.
	 * Guarda comando no vetor de amostras recebidas
	 * Comandos:<br>
	 * 0 nenhum comando ou comando nao reconhecido<br>
	 * 1 TOP-LEFT<br> 2 BOTTOM-LEFT<br>3 TOP-RIGHT<br>4 BOTTOM-RIGHT<br>5 TOP-LEFT + TOP-RIGHT<br>
	 * 6 TOP-LEFT + BOTTOM-RIGHT<br>7 BOTTOM-LEFT + TOP-RIGHT<br>8 BOTTOM-LEFT + BOTTOM-RIGHT<br>
	 * 9 CENTRE/BEACON<br>10 BOTTOM-LEFT + TOP-LEFT<br>11 TOP-RIGHT + BOTTOM-RIGHT<br><br>
	 * 
	 * 2 - Para modo de detector de distancia
	 * 
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return numero do botao (ou combinacao de botoes) : int
	 */
	public int coletaAmostra(float[] amostrasRecebidas) {
		if(this.modoOperativo > 0)
		{
			this.receptorAmostra = sensor.getDistanceMode();
			this.sensor.fetchSample(amostrasRecebidas, this.offset);
			return (int) amostrasRecebidas[this.offset];
			
		}else
		{
			this.comandoRecebido = sensor.getRemoteCommand(this.canalControle);
			amostrasRecebidas[this.offset] = this.comandoRecebido;
			return this.comandoRecebido;
		}
	}
	
	/**
	 * Metodo que coleta o comando do controle remoto
	 * OBS1: ele automaticamente muda o modo de operacao para receptor de controle
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return comando : int
	 */
	public int getComandoControle(float[] amostrasRecebidas)
	{
		this.modoOperativo = 0;
		this.comandoRecebido = coletaAmostra(amostrasRecebidas);
		return this.comandoRecebido;
	}
	
	/**
	 * Metodo que coleta a distancia de algum objeto ou superficie do sensor infravermelho.
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return distancia : int em cm
	 */
	public int getDistancia(float[] amostrasRecebidas)
	{
		this.modoOperativo = 1;
		return coletaAmostra(amostrasRecebidas);
	}
	
	@Override
	public void closeSensor() 
	{
		this.sensor.close();
	}
}
