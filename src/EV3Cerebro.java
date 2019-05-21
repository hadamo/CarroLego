import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class EV3Cerebro{
		LocalEV3 brick;
		
		/**
		 * Acessa EV3 local e retorna porta, dada nome da porta.
		 * Para porta de sensor: S1, S2, S3, S4
		 * Para porta de motor: A, B, C, D
		 * @param porta
		 * @return Port porta
		 */
		public Port getPorta(String porta)
		{
			return brick.getPort(porta);
		}
		public void teste() 
		{
			EV3ColorSensor sensor;
			EV3TouchSensor sensor2;
			Port port = brick.getPort("S4");
			Port porta = brick.getPort("S3");
			sensor = new EV3ColorSensor(port);
			sensor2 = new EV3TouchSensor(porta);
			SampleProvider sp = sensor.getColorIDMode();
			SampleProvider ss = sensor2.getTouchMode();
			float sample[] = new float[sp.sampleSize() + ss.sampleSize()];
			sensor.fetchSample(sample, 0);
			sensor2.fetchSample(sample, 1);
		}
		
		
		/**
		 * espera qtd T  de segundos
		 * @param Tsegundos
		 */
		public void esperaSegundos(int segundos)
		{
			Delay.msDelay(segundos*1000);
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
		
		/*
		public void corLed(int cor)
		{
			Button.LEDPattern(1);
		}*/
		
	
}
