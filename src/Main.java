import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

import SimpleNetworking.Client;
import SortLib.SortDisplayer;
import SortLib.SortTracker;

public class Main {

	public Main() {
	}

	static boolean runWithServer = false;
	static int elementCount = 50;
	static int seed = 0;
	static int style = 1;

	/** main program (entry point) */
	public static void main(String[] args) {
		Client client = null;
		StartupConfig startupConfig = null;

		if (runWithServer){
			client = new Client("127.0.0.1",8080);
			startupConfig = (StartupConfig) client.waitForNextObject().get(0);
		}
		else{
			startupConfig = new StartupConfig(elementCount, seed, style);
		}
		SortTracker st = new SortTracker(startupConfig.seed,startupConfig.elementCount,startupConfig.style);

		// new SelectionSort(st.addSolution());
		new BubbleSort(st.addSolution());
		// new InsertionSort(st.addSolution());
		// new HeapSort(st.addSolution());
		// new QuickSort(st.addSolution());
		// new CombSort(st.addSolution());

		if (runWithServer){
			client.sendObject(st);
		}

		SortDisplayer sd = new SortDisplayer(st);
	}


}

class StartupConfig implements Serializable{
	final int code = 101;
	int elementCount = 100;
	int seed = 0;
	int style = 0;

	public StartupConfig(int elementCount, int seed, int style){
		this.elementCount = elementCount;
		this.seed = seed;
		this.style = style;
	}
}