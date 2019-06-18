import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class SensorCor extends Sensor {
	private EV3ColorSensor sensor;
	
	public SensorCor(int offSet, int porta)
	{
		super(offSet);
		if(porta == 3)	this.sensor = new EV3ColorSensor(SensorPort.S3);
		if(porta == 1)  this.sensor = new EV3ColorSensor(SensorPort.S1);
		this.setAmostra(-1);  
	}




	@Override
	/**
	 * Metodo que faz a coleta de amostra do sensor de cor, no modo GetColorId
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return id de cor, 6 para Branco, 7 para Preto
	 */
	public int coletaAmostra(float[] amostrasRecebidas) {
		this.receptorAmostra = sensor.getColorIDMode();
		this.sensor.fetchSample(amostrasRecebidas, this.offset);
		return (int) amostrasRecebidas[this.offset];
	}
	
	/**
	 * Metodo que verifica se a cor da superficie analisada eh branca<br>
	 * recebe vetor de amostrasRecebidas quando usado com outros sensores<br>
	 * ao mesmo tempo.
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return true para branco, false para diferente : boolean
	 */
	public boolean isBranco(float[] amostrasRecebidas)
	{
		if(this.coletaAmostra(amostrasRecebidas) == 6) return true;
		else return false;
	}
	
	/**
	 * Metodo que verifica se a cor da superficie analisada eh preta
	 * recebe vetor de amostrasRecebidas quando usado com outros sensores<br>
	 * ao mesmo tempo
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return true para preto, false para diferente : boolean
	 */
	public boolean isPreto(float[] amostrasRecebidas)
	{
		if(this.coletaAmostra(amostrasRecebidas) == 7) return true;
		else return false;
	}
	
	/**
	 * Metodo que retorna o ID da cor analisada: <br> -1 nenhuma cor identificada;<br> 0 vermelho;<br> 
	 * 1 verde;<br> 2 azul;<br> 3 amarelo;<br> 4 magenta;<br>  5 laranja;<br>   6 branco;<br> 7 preto;<br>  
	 * 8 rosa;<br> 9 cinza;<br> 10 cinza claro;<br> 11 cinza escuro;<br> 12 cian;<br> 13 marrom.<br> 
	 *  *OBS1: O sensor deve estar entre 1cm e 3cm da superficie.
	 *  *OBS2: ordem das cores vem da biblioteca LeJOS.
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return id da cor : int
	 */
	public int getIdCor(float[] amostrasRecebidas)
	{
		return this.coletaAmostra(amostrasRecebidas);
	}

	/**
	 * Metodo que retorna o nome da cor analisada ou se não conseguiu identificar a cor.
	 * Melhor utilizado para identificar preto e branco por condições locais.
	 * Recebe vetor de amostras recebidas quando usado com outros sensores
	 * @param amostrasRecebidas : float[] vetor que recebe as amostras <br> compartilhadas entre 1 ou mais sensores.
	 * @return nome da cor : String
	 */
	public String getNomeCor(float[] amostrasRecebidas)
	{
		int IDcor = this.coletaAmostra(amostrasRecebidas);
		String cor;
		switch(IDcor)
		{
			case 0: cor = "Vermelho " ;
			case 1: cor = "Verde" ;
			case 2: cor = "Azul" ;
			case 3: cor = "Amarelo" ;
			case 4: cor = "Magenta" ;
			case 5: cor = "Laranja" ;
			case 6: cor = "Branco" ;
			case 7: cor = "Preto" ;
			case 8: cor = "Rosa" ;
			case 9: cor = "Cinza" ;
			case 10: cor = "Cinza Claro" ;
			case 11: cor = "Cinza Escuro" ;
			case 12: cor = "Ciano" ;
			case 13: cor = "Marrom" ;
			default: cor ="Nao sei a cor!";
		}
		return cor;
	}
	
	@Override
	public void closeSensor() 
	{
		this.sensor.close();
	}
}
