import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;

public class SensorInfravermelho extends Sensor {
	private EV3IRSensor sensor;
	private int canalPadrao;
	private int comandoRecebido;
	private int comandoPadrao;
	public SensorInfravermelho(int offSet)
	{
		super(offSet);
		this.sensor = new EV3IRSensor(SensorPort.S2);
		this.setAmostra(-1);
		this.canalPadrao = 0;
		this.comandoRecebido = -1;
		this.comandoPadrao = 0;
	}
	public int getCanalPadrao()
	{
		return this.canalPadrao;
	}
	
	/**
	 * Set canal Padrão para controle remoto, deve ser<br>
	 * valor no intervalo [0,3].<br>
	 * *OBS: No controle os canais estao em [1,4]
	 * @param <INT>
	 */
	public void setCanalPadrao(int novoCanal)
	{
		if(novoCanal>=0 && novoCanal<4) this.canalPadrao = novoCanal;
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
	 * Metodo que recebe o comando do CONTROLE REMOTO pelo sensor INFRAVERMELHO,<br>
	 * Apenas para o canal atualmente definido como Padrao.
	 * Guarda comando no vetor de amostras recebidas
	 * Comandos:<br>
	 * 1 TOP-LEFT<br> 2 BOTTOM-LEFT<br>3 TOP-RIGHT<br>4 BOTTOM-RIGHT<br>5 TOP-LEFT + TOP-RIGHT<br>
	 * 6 TOP-LEFT + BOTTOM-RIGHT<br>7 BOTTOM-LEFT + TOP-RIGHT<br>8 BOTTOM-LEFT + BOTTOM-RIGHT<br>
	 * 9 CENTRE/BEACON<br>10 BOTTOM-LEFT + TOP-LEFT<br>11 TOP-RIGHT + BOTTOM-RIGHT
	 * @param amostrasRecebidas vetor de amostras para multiplos sensores
	 * @return numero do botao (ou combinacao de botoes) : int
	 */
	public int coletaAmostra(float[] amostrasRecebidas) {
		this.comandoRecebido = sensor.getRemoteCommand(this.canalPadrao);
		amostrasRecebidas[this.offset] = this.comandoRecebido;
		return this.comandoRecebido;
	}
	@Override
	/**
	 * Metodo que recebe o comando do CONTROLE REMOTO pelo sensor INFRAVERMELHO,<br>
	 * Apenas para o canal atualmente definido como Padrao.
	 * Comandos:<br>
	 * 1 TOP-LEFT<br> 2 BOTTOM-LEFT<br>3 TOP-RIGHT<br>4 BOTTOM-RIGHT<br>5 TOP-LEFT + TOP-RIGHT<br>
	 * 6 TOP-LEFT + BOTTOM-RIGHT<br>7 BOTTOM-LEFT + TOP-RIGHT<br>8 BOTTOM-LEFT + BOTTOM-RIGHT<br>
	 * 9 CENTRE/BEACON<br>10 BOTTOM-LEFT + TOP-LEFT<br>11 TOP-RIGHT + BOTTOM-RIGHT
	 * @return numero do botao (ou combinacao de botoes) : int
	 */	
	public int coletaAmostra() {
		this.comandoRecebido = sensor.getRemoteCommand(this.canalPadrao);
		return this.comandoRecebido;
	}
	/**
	 * Metodo que recebe o comando do CONTROLE REMOTO pelo sensor INFRAVERMELHO,<br>
	 * Apenas para o canal atualmente definido como Padrao.
	 * Comandos:<br>
	 * 1 TOP-LEFT<br> 2 BOTTOM-LEFT<br>3 TOP-RIGHT<br>4 BOTTOM-RIGHT<br>5 TOP-LEFT + TOP-RIGHT<br>
	 * 6 TOP-LEFT + BOTTOM-RIGHT<br>7 BOTTOM-LEFT + TOP-RIGHT<br>8 BOTTOM-LEFT + BOTTOM-RIGHT<br>
	 * 9 CENTRE/BEACON<br>10 BOTTOM-LEFT + TOP-LEFT<br>11 TOP-RIGHT + BOTTOM-RIGHT
	 * @param canal canal selecionado no controle remoto entre 0 e 3 <br> OBS: no controle informa entre 1 e 4
	 * @return numero do botao (ou combinacao de botoes) : int
	 */
	public int coletaAmostra(int canal) {
		if(canal < 0 || canal >3)
		{
			System.err.println("Valor Invalido, insira valor x em [0,3]");
			return this.comandoPadrao;
		}else
		{
			this.comandoRecebido = sensor.getRemoteCommand(canal);
			return this.comandoRecebido;
		}
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
