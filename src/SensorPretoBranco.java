import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class SensorPretoBranco extends Sensor {
	private EV3ColorSensor sensor;
	
	public SensorPretoBranco(int offSet)
	{
		super(offSet);
		this.sensor = new EV3ColorSensor(SensorPort.S3);
		this.setAmostra(-1);
	}


	@Override
	/**
	 * Metodo que faz a coleta de amostra do sensor de cor, no modo GetColorId
	 * @return id de cor, 6 para Branco, 7 para Preto
	 */
	public int coletaAmostra(float[] amostrasRecebidas) {
		this.receptorAmostra = sensor.getColorIDMode();
		this.sensor.fetchSample(amostrasRecebidas, this.offset);
		return (int) amostrasRecebidas[this.offset];
	}
	
	public int coletaAmostra() {
		this.receptorAmostra = sensor.getColorIDMode();
		float []amostras = new float[receptorAmostra.sampleSize()];
		this.sensor.fetchSample(amostras, 0);
		return (int) amostras[0];
	}
	
	/**
	 * Metodo que verifica se a cor da superficie analisada eh branca<br>
	 * recebe vetor de amostrasRecebidas quando usado com outros sensores<br>
	 * ao mesmo tempo.
	 * @return true para branco, false para diferente : boolean
	 */
	public boolean isBranco(float[] amostrasRecebidas)
	{
		if(this.coletaAmostra(amostrasRecebidas) == 6) return true;
		else return false;
	}
	/**
	 * Metodo que verifica se a cor da superficie analisada eh branca
	 * @return true ou false
	 */
	public boolean isBranco()
	{
		if(this.coletaAmostra() == 6) return true;
		else return false;
	}
	
	/**
	 * Metodo que verifica se a cor da superficie analisada eh preta
	 * recebe vetor de amostrasRecebidas quando usado com outros sensores<br>
	 * ao mesmo tempo
	 * @return true para preto, false para diferente : boolean
	 */
	public boolean isPreto(float[] amostrasRecebidas)
	{
		if(this.coletaAmostra(amostrasRecebidas) == 7) return true;
		else return false;
	}
	
	/**
	 * Metodo que verifica se a cor da superficie analisada eh branca
	 * @return true ou false
	 */
	public boolean isPreto()
	{
		if(this.coletaAmostra() == 7) return true;
		else return false;
	}
	
	/**
	 * Metodo que retorna o ID da cor analisada: <br> -1 nenhuma cor identificada;<br> 0 vermelho;<br> 
	 * 1 verde;<br> 2 azul;<br> 3 amarelo;<br> 4 magenta;<br>  5 laranja;<br>   6 branco;<br> 7 preto;<br>  
	 * 8 rosa;<br> 9 cinza;<br> 10 cinza claro;<br> 11 cinza escuro;<br> 12 cian;<br> 13 marrom.<br> 
	 *  *Observacao: O sensor deve estar no min. 3cm da superficie
	 * @return id da cor : int
	 */
	public int getIdCor(float[] amostrasRecebidas)
	{
		return this.coletaAmostra(amostrasRecebidas);
	}
	
	/**
	 * Metodo que retorna o ID da cor analisada: <br> -1 nenhuma cor identificada;<br> 0 vermelho;<br> 
	 * 1 verde;<br> 2 azul;<br> 3 amarelo;<br> 4 magenta;<br>  5 laranja;<br>   6 branco;<br> 7 preto;<br>  
	 * 8 rosa;<br> 9 cinza;<br> 10 cinza claro;<br> 11 cinza escuro;<br> 12 cian;<br> 13 marrom.<br> 
	 *  *Observacao: O sensor deve estar no min. 3cm da superficie
	 * @return id da cor : int
	 */
	public int getIdCor()
	{
		return this.coletaAmostra();
	}

	/**
	 * Metodo que retorna o nome da cor analisada ou se n�o conseguiu identificar a cor.
	 * Melhor utilizado para identificar preto e branco por condi��es locais.
	 * Recebe vetor de amostras recebidas quando usado com outros sensores
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
	/**
	 * Metodo que retorna o nome da cor analisada ou se n�o conseguiu identificar a cor.
	 * Melhor utilizado para identificar preto e branco por condi��es locais.
	 * @return nome da cor : String
	 */
	public String getNomeCor()
	{
		int IDcor = this.coletaAmostra();
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
	
	public void setReceptor()
	{
		this.receptorAmostra = this.sensor.getColorIDMode();
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
