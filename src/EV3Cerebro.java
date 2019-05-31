import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class EV3Cerebro{		
	
		/**
		 * espera qtd T  de segundos
		 * @param Tsegundos
		 */
		public void esperaSegundos(int segundos)
		{
			Delay.msDelay(segundos*1000);
		}
		
		public void esperaMilissegundos(int ms)
		{
			Delay.msDelay(ms);
		}
		
		public void beep1()
		{
			Sound.beepSequenceUp();
		}
		
		public void beep2()
		{
			Sound.beepSequence();
		}
		
		public void beep3()
		{
			Sound.beep();
		}
		
		public void beep4()
		{
			Sound.buzz();
		}
		
		public void beep5()
		{
			Sound.twoBeeps();
		}
		
	/**
	 * 1-9
	 * @param cor
	 */
		public void corLed(int cor)
		{
			Button.LEDPattern(cor);
		}
		
	
}
