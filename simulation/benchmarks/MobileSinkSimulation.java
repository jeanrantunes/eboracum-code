package eboracum.simulation.benchmarks;

import eboracum.simulation.BenchmarksGenerator;
import eboracum.simulation.util.HistogramSpectrogramFactory;

public class MobileSinkSimulation extends BenchmarksGenerator{

	@Override
	protected void runBenchmarks() {
		String simulationIdentification = "jean";
		this.scenarioDimensionXY = new int[]{1000,1000};
		HistogramSpectrogramFactory.newInvertNormalSpectrogram(this.scenarioDimensionXY[0]-100, this.scenarioDimensionXY[0]-100, "spectStartPosition.csv");
		this.initBattery = 5400/2;
		HistogramSpectrogramFactory.newHistogram(120, "triggerTimeHist.csv");
		this.commCover = 160;
		this.sensorCover = 120;
		int numOfNodes = 10;
		this.cpuCost = 50;
		this.idleCost = 0.3;
		this.nodesRandomizeFlag = false;
		if (!nodesRandomizeFlag) generateGridPosition(numOfNodes);
		this.mainGatewayCenteredFlag = true;
		this.wirelessSensorNodesType = "GeneralType";
		this.wirelessNodes.put("sensor.SimpleWSNNode", numOfNodes);
		this.wirelessEvents.put(new WirelessEvent("E0", 0.0018, false,"{1.0, 0.0, 0.0, 1.0}", "<task id=\"0\"><cpu name=\"SimpleFIFOBasedCPU\" cost=\"1\"/></task>", "StochasticStaticEvent"), 20);
		this.wirelessEvents.put(new WirelessEvent("E1", 0.0018, false,"{0.0, 0.0, 1.0, 1.0}", "<task id=\"0\"><cpu name=\"SimpleFIFOBasedCPU\" cost=\"2\"/></task>", "StochasticStaticEvent"), 20);
		generateEventsXML();
		this.network = "SimpleAdHocNetwork";
		this.rebuildNetworkWhenGatewayDies= false;
		this.synchronizedRealTime = true;
		generateModel(simulationIdentification);
		try {
			this.run("jean",2);
			generateEventsXML();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unused")
	public static void main(String[] args){
		BenchmarksGenerator b = new MobileSinkSimulation();
	}
}
