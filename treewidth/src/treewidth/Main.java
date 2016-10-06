package treewidth;

import java.io.IOException;

public class Main {
	public static void main(String[]args) throws IOException {
		
		String[] fileNames = {
				"two-vertices",
				"two-vertices-2",
				"single-vertex",
				"empty",
				"single-edge",
				"wedge",
				"web4",
				"web3",
				"web2",
				"p-num-vertices-larger",
				//"gr-only", TREE DECOMPOSITION DOES NOT EXIST
				"TetrahedralGraph",
				"HouseGraph",
				"HouseXGraph",
				"ThomsenGraph",
				"PasechnikGraph_1",
				"MoserSpindle",
				"WagnerGraph",
				"OctahedralGraph",
				"HexahedralGraph",
				"T2starGeneralizedQuadrangleGraph_2",
				"SwitchedSquaredSkewHadamardMatrixGraph_1",
				"PetersenGraph",
				"OddGraph_3",
				"SquaredSkewHadamardMatrixGraph_1",
				"KrackhardtKiteGraph",
				"HerschelGraph",
				"TruncatedTetrahedralGraph",
				"TietzeGraph",
				"FruchtGraph",
				"FranklinGraph",
				"DurerGraph",
				"BidiakisCube",
				"GrotzschGraph",
				"HeawoodGraph",
				"CirculantGraph_20_5",
				"ChvatalGraph",
				"LollipopGraph_7_5",
				"GoldnerHararyGraph",
				"MoebiusKantorGraph",
				"SierpinskiGasketGraph_3",
				"web1",
				"SousselierGraph",
				"PappusGraph",
				"IcosahedralGraph",
				"BlanusaSecondSnarkGraph",
				"BlanusaFirstSnarkGraph",
				"OrthogonalArrayBlockGraph_4_3",
				"GeneralizedPetersenGraph_10_4",
				"FlowerSnark",
				"DodecahedralGraph",
				"DesarguesGraph",
				"HoffmanGraph",
				"FriendshipGraph_10",
				"PoussinGraph",
				"NauruGraph",
				"McGeeGraph",
				"MarkstroemGraph",
				"RobertsonGraph",
				"ClebschGraph",
				"FolkmanGraph",
				"F26AGraph",
				"Grid2dGraph_5_5",
				"OrthogonalPolarGraph_5_2",
				"HararyGraph_6_15",
				"BrinkmannGraph",
				"ErreraGraph",
				"CoxeterGraph",
				"ShrikhandeGraph",
				"TutteCoxeterGraph",
				"DoubleStarSnark",
				"DyckGraph",
				"NonisotropicOrthogonalPolarGraph_3_5",
				"HoltGraph",
				"LadderGraph_20",
				"KittellGraph",
				"CircularLadderGraph_20",
				"PaleyGraph_17",
				"GoethalsSeidelGraph_2_3",
				"WienerArayaGraph",
				"Toroidal6RegularGrid2dGraph_4_6",
				//"OddGraph_4", TAKES VERY LONG TIME
				"TutteGraph",
				"HyperStarGraph_10_2",
				"WatkinsSnarkGraph",
				"SzekeresSnarkGraph",
				//"WellsGraph", TAKES VERY LONG TIME
				"Klein7RegularGraph",
				//"GrayGraph", TAKES VERY LONG TIME
				"EllinghamHorton54Graph",
				//"SylvesterGraph", TAKES VERY LONG TIME
				"StarGraph_100",
				"CompleteGraph_15",
				"BarbellGraph_10_5",
				"PathGraph_100",
				"CycleGraph_100",
				"HarborthGraph",
				"EllinghamHorton78Graph",
				//"NKStarGraph_5_3", TAKES VERY LONG TIME
				"RingedTree_6",
				"TaylorTwographDescendantSRG_3",
				"AhrensSzekeresGeneralizedQuadrangleGraph_3",
				"MeredithGraph",
				"HortonGrap",
				"JohnsonGraph_8_2",
				"FibonacciTree_10",
				//"HanoiTowerGraph_4_3", TAKES VERY LONG TIME
				//"HoffmanSingletonGraph", TAKES VERY LONG TIME
				"WheelGraph_100",
				"TaylorTwographSRG_3",
				"SchlaefliGraph",
				//"SymplecticPolarGraph_4_3", TAKES VERY LONG TIME
				//"SymplecticDualPolarGraph_4_3", TAKES VERY LONG TIME
				"SierpinskiGasketGraph_5",
				//"KneserGraph_8_3",
				"WorldMap",
				//"PasechnikGraph_2", TAKES VERY LONG TIME
				"BalancedTree_3_5",
				//"CompleteBipartiteGraph_25_20", TAKES VERY LONG TIME
				//"SwitchedSquaredSkewHadamardMatrixGraph_2", TAKES VERY LONG TIME
				//"T2starGeneralizedQuadrangleGraph_4", TAKES VERY LONG TIME
				//"SquaredSkewHadamardMatrixGraph_2", TAKES VERY LONG TIME
				//"KneserGraph_10_2", TAKES VERY LONG TIME
				//"GossetGraph", TAKES VERY LONG TIME
				//"NonisotropicUnitaryPolarGraph_3_3", TAKES VERY LONG TIME
				"DorogovtsevGoltsevMendesGraph"
				};
		
		Parser parser = new Parser();
		
		for(String fileName : fileNames) {
			
			long startTime = System.currentTimeMillis();
			
			Graph graph = parser.parseGraph("./data/" + fileName + ".gr");
			TreeDecompositionGraph tree = parser.parseTree("./data/" + fileName + ".td", graph);
			
			Algorithm algorithm = new Algorithm(graph, tree);
			int maxIndependentSet = algorithm.findMaxIndependentSet();
			
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			
//			System.out.println(
//					"File: " + fileName + "\n" + 
//					"Width: " + tree.getWidth() + "\n" + 
//					"MIS: " + maxIndependentSet + "\n" + 
//					"time: " + duration + "\n");
			
			System.out.println(
					fileName + " & $" + graph.getNumberOfVertices() + "$ & " + 
					"$" + tree.getWidth() + "$ & $" + maxIndependentSet + "$ \\\\");
		
		}
	
	}
	
	

}
