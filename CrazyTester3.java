package assignment4Graph;

public class CrazyTester3 {
	static int errors = 0;
	
	public static void main(String[] args) {
		
		Graph g0 = new Graph(0); // 0 vertices
		Graph g1 = new Graph(1); // 1 vertex
		Graph g2 = new Graph(2); // 2 vertices
		Graph g10 = new Graph(10); // 10 vertices
		Graph g16 = new Graph(16); // 16 vertices
		Graph g23 = new Graph(23); // 23 vertices
		
		HEADER("============================================================================");
		HEADER(
			"COMP 250 ASSIGNMENT 4 TESTER\n"
			+ "This is a tester only for the graph section.\n"
			+ "If you see true, it means you're good.\n"
			+ "If you see false, that means something's wrong.\n"
			+ "The very bottom of the tester will tell you how many errors you have left (if there are any).\n"
			+ "There are a lot of tests here. For cycle and shortestPath, there's a good chance some of the cases won't be tested."
			+ "If you find any errors or you have questions, plz let me know: jeffery.tang@mail.mcgill.ca or Facebook if you know me."
		);
		HEADER("============================================================================");
		
		HEADER("-------------------------------------------");
		HEADER("TEST ADDEDGE, REMOVEEDGE, NBEDGES TOGETHER");
		HEADER("-------------------------------------------");
		
		HEADER("G0");
		print(g0.nbEdges() == 0);
		
		HEADER("G1");
		print(!g1.adjacency[0][0]);
		print(g1.nbEdges() == 0);
		
		g1.addEdge(0, 0);
		print(g1.adjacency[0][0]);
		print(g1.nbEdges() == 1);
		
		g1.removeEdge(0, 0);
		print(g1.nbEdges() == 0);
		
		HEADER("G2");
		g2.addEdge(0, 1);
		print(g2.adjacency[0][1] && g2.adjacency[1][0]);
		print(g2.nbEdges() == 1);
		
		g2.removeEdge(1, 0);
		print(!(g2.adjacency[0][1] && g2.adjacency[1][0]));
		print(g2.nbEdges() == 0);
		
		HEADER("G10");
		HEADER("Add Edges");
		for (int i = 0; i < g10.nbNodes; i++) {
			for (int j = 0; j < g10.nbNodes; j++) {
				g10.addEdge(i, j);
				print(g10.adjacency[i][j] && g10.adjacency[j][i]);
			}
		}
		
		HEADER("Number of Edges (INCLUDING SELF-LOOPS)");
		print(g10.nbEdges() == 55);
		
		HEADER("Remove Edges");
		for (int i = 0; i < g10.nbNodes; i++) {
			for (int j = 0; j < g10.nbNodes; j++) {
				g10.removeEdge(i, j);
				print(!(g10.adjacency[i][j] && g10.adjacency[j][i]));
			}
		}
		
		HEADER("Number of Edges");
		print(g10.nbEdges() == 0);
		
		HEADER("Add Edges");
		for (int i = 0; i < g10.nbNodes; i++) {
			for (int j = 0; j < g10.nbNodes; j++) {
				g10.addEdge(j, i);
				print(g10.adjacency[i][j] && g10.adjacency[j][i]);
			}
		}
		
		HEADER("Number of Edges (INCLUDING SELF-LOOPS)");
		print(g10.nbEdges() == 55);
		
		HEADER("Remove Edges");
		for (int i = 0; i < g10.nbNodes; i++) {
			for (int j = 0; j < g10.nbNodes; j++) {
				g10.removeEdge(j, i);
				print(!(g10.adjacency[i][j] && g10.adjacency[j][i]));
			}
		}
		
		HEADER("Number of Edges");
		print(g10.nbEdges() == 0);
		
		HEADER("-------------------------------------");
		HEADER("TEST CYCLE (SELF-LOOP IS NOT A CYCLE)");
		HEADER("-------------------------------------");
		
		HEADER("G1");
		print(!g1.cycle(0));
		g1.addEdge(0, 0);
		print(!g1.cycle(0));
		g1.removeEdge(0, 0);
		print(!g1.cycle(0));
		
		HEADER("G2");
		print(!g2.cycle(0));
		print(!g2.cycle(1));
		g2.addEdge(1, 0);
		print(!g2.cycle(0));
		print(!g2.cycle(1));
		g2.removeEdge(0, 1);
		print(!g2.cycle(0));
		print(!g2.cycle(1));
		
		HEADER("G10");
		g10.addEdge(1, 2);
		g10.addEdge(1, 7);
		g10.addEdge(1, 8);
		g10.addEdge(2, 8);
		g10.addEdge(4, 6);
		g10.addEdge(5, 5);
		g10.addEdge(5, 6);
		g10.addEdge(6, 9);
		g10.addEdge(7, 9);
		g10.addEdge(8, 9);
		print(!g10.cycle(0));
		print(g10.cycle(1));
		print(g10.cycle(2));
		print(!g10.cycle(3));
		print(!g10.cycle(4));
		print(!g10.cycle(5));
		print(!g10.cycle(6));
		print(g10.cycle(7));
		print(g10.cycle(8));
		print(g10.cycle(9));
		
		HEADER("G23");
		g23.addEdge(8, 9);
		g23.addEdge(8, 7);
		g23.addEdge(9, 7);
		g23.addEdge(7, 4);
		g23.addEdge(4, 5);
		g23.addEdge(5, 11);
		g23.addEdge(5, 6);
		g23.addEdge(11, 6);
		g23.addEdge(4, 0);
		g23.addEdge(0, 13);
		g23.addEdge(13, 18);
		g23.addEdge(18, 19);
		g23.addEdge(19, 20);
		g23.addEdge(20, 14);
		g23.addEdge(14, 0);
		g23.addEdge(4, 15);
		g23.addEdge(15, 21);
		g23.addEdge(15, 16);
		g23.addEdge(21, 16);
		g23.addEdge(16, 17);
		g23.addEdge(17, 22);
		g23.addEdge(22, 15);
		g23.addEdge(15, 17);
		g23.addEdge(10, 1);
		g23.addEdge(1, 12);
		g23.addEdge(12, 2);
		g23.addEdge(2, 3);
		g23.addEdge(3, 3);
		print(g23.cycle(0));
		print(!g23.cycle(1));
		print(!g23.cycle(2));
		print(!g23.cycle(3));
		print(!g23.cycle(4));
		print(g23.cycle(5));
		print(g23.cycle(6));
		print(g23.cycle(7));
		print(g23.cycle(8));
		print(g23.cycle(9));
		print(!g23.cycle(10));
		print(g23.cycle(11));
		print(!g23.cycle(12));
		print(g23.cycle(13));
		print(g23.cycle(14));
		print(g23.cycle(15));
		print(g23.cycle(16));
		print(g23.cycle(17));
		print(g23.cycle(18));
		print(g23.cycle(19));
		print(g23.cycle(20));
		print(g23.cycle(21));
		print(g23.cycle(22));
		g23.addEdge(18, 8);
		print(g23.cycle(4));
		g23.removeEdge(18, 8);
		
		HEADER("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		HEADER("TEST SHORTEST PATH\nIF START == END:\nIF START HAS A SELF-LOOP, RETURN 1\nELSE IF START IS PART OF A CYCLE, RETURN CYCLE LENGTH\nELSE IF START IS NOT PART OF A CYCLE, RETURN NBEDGES + 1");
		HEADER("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		HEADER("G10");
		
		HEADER("STARTING FROM VERTEX 0");
		for (int i = 0; i < g10.nbNodes; i++) {
			print(g10.shortestPath(0, i) == 11);
		}
		
		HEADER("STARTING FROM VERTEX 1");
		print(g10.shortestPath(1, 0) == 11);
		print(g10.shortestPath(1, 1) == 3);
		print(g10.shortestPath(1, 2) == 1);
		print(g10.shortestPath(1, 3) == 11);
		print(g10.shortestPath(1, 4) == 4);
		print(g10.shortestPath(1, 5) == 4);
		print(g10.shortestPath(1, 6) == 3);
		print(g10.shortestPath(1, 7) == 1);
		print(g10.shortestPath(1, 8) == 1);
		print(g10.shortestPath(1, 9) == 2);
		
		HEADER("G16");
		
		HEADER("STARTING FROM VERTEX 1");
		g16.addEdge(1, 2);
		g16.addEdge(1, 3);
		g16.addEdge(2, 3);
		g16.addEdge(2, 4);
		g16.addEdge(2, 5);
		g16.addEdge(3, 5);
		g16.addEdge(3, 6);
		g16.addEdge(4, 5);
		g16.addEdge(5, 6);
		g16.addEdge(4, 7);
		g16.addEdge(4, 8);
		g16.addEdge(7, 8);
		g16.addEdge(5, 8);
		g16.addEdge(5, 9);
		g16.addEdge(8, 9);
		g16.addEdge(6, 9);
		g16.addEdge(6, 10);
		g16.addEdge(9, 10);
		g16.addEdge(7, 11);
		g16.addEdge(7, 12);
		g16.addEdge(11, 12);
		g16.addEdge(8, 12);
		g16.addEdge(8, 13);
		g16.addEdge(12, 13);
		g16.addEdge(9, 13);
		g16.addEdge(9, 14);
		g16.addEdge(13, 14);
		g16.addEdge(10, 14);
		g16.addEdge(10, 15);
		g16.addEdge(14, 15);
		g16.addEdge(13, 0);
		print(g16.shortestPath(1, 1) == 3);
		print(g16.shortestPath(1, 2) == 1);
		print(g16.shortestPath(1, 3) == 1);
		print(g16.shortestPath(1, 4) == 2);
		print(g16.shortestPath(1, 5) == 2);
		print(g16.shortestPath(1, 6) == 2);
		print(g16.shortestPath(1, 7) == 3);
		print(g16.shortestPath(1, 8) == 3);
		print(g16.shortestPath(1, 9) == 3);
		print(g16.shortestPath(1, 10) == 3);
		print(g16.shortestPath(1, 11) == 4);
		print(g16.shortestPath(1, 12) == 4);
		print(g16.shortestPath(1, 13) == 4);
		print(g16.shortestPath(1, 14) == 4);
		print(g16.shortestPath(1, 15) == 4);
		print(g16.shortestPath(1, 0) == 5);
		
		HEADER("G23");
		
		HEADER("STARTING FROM VERTEX 10");
		print(g23.shortestPath(10, 10) == 24 && g23.shortestPath(3, 3) == 1);
		print(g23.shortestPath(10, 1) == 1 && g23.shortestPath(3, 2) == 1);
		print(g23.shortestPath(10, 12) == 2 && g23.shortestPath(3, 12) == 2);
		print(g23.shortestPath(10, 2) == 3 && g23.shortestPath(3, 1) == 3);
		print(g23.shortestPath(10, 3) == 4 && g23.shortestPath(3, 10) == 4);
		
		HEADER("STARTING FROM VERTEX 4");
		print(g23.shortestPath(4, 4) == 24);
		print(g23.shortestPath(4, 7) == 1);
		print(g23.shortestPath(4, 8) == 2);
		print(g23.shortestPath(4, 9) == 2);
		print(g23.shortestPath(4, 5) == 1);
		print(g23.shortestPath(4, 11) == 2);
		print(g23.shortestPath(4, 6) == 2);
		print(g23.shortestPath(4, 0) == 1);
		print(g23.shortestPath(4, 13) == 2);
		print(g23.shortestPath(4, 14) == 2);
		print(g23.shortestPath(4, 18) == 3);
		print(g23.shortestPath(4, 20) == 3);
		print(g23.shortestPath(4, 19) == 4);
		print(g23.shortestPath(4, 15) == 1);
		print(g23.shortestPath(4, 21) == 2);
		print(g23.shortestPath(4, 22) == 2);
		print(g23.shortestPath(4, 16) == 2);
		print(g23.shortestPath(4, 17) == 2);
		print(g23.shortestPath(4, 10) == 24);
		print(g23.shortestPath(4, 1) == 24);
		print(g23.shortestPath(4, 12) == 24);
		print(g23.shortestPath(4, 2) == 24);
		print(g23.shortestPath(4, 3) == 24);
		
		HEADER("FARTHEST DISTANCE");
		print(g23.shortestPath(19, 16) == 6);
		print(g23.shortestPath(19, 17) == 6);
		print(g23.shortestPath(19, 21) == 6);
		print(g23.shortestPath(19, 22) == 6);
		
		HEADER("EXTRA");
		print(g23.shortestPath(0, 0) == 6);
		print(g23.shortestPath(13, 13) == 6);
		print(g23.shortestPath(19, 19) == 6);
		g23.addEdge(14, 14);
		print(g23.shortestPath(14, 14) == 1);
		g23.addEdge(14, 19);
		print(g23.shortestPath(14, 14) == 1);
		g23.removeEdge(14, 14);
		g23.removeEdge(14, 19);
		g23.removeEdge(18, 19);
		print(g23.shortestPath(18, 18) == 24);
		g23.addEdge(18, 19);
		g23.addEdge(13, 7);
		print(g23.shortestPath(7, 7) == 3);  //System.out.println("I got "+g23.shortestPath(7, 7));
		g23.removeEdge(13, 7);
		g23.addEdge(18, 8);
		g23.addEdge(14, 15);
		g23.addEdge(6, 21);
		g23.addEdge(9, 11);
		print(g23.shortestPath(4, 4) == 4);
		g23.addEdge(4, 4);
		print(g23.shortestPath(4, 4) == 1);
		g23.addEdge(4, 4);
		g23.removeEdge(18, 8);
		g23.removeEdge(14, 15);
		g23.removeEdge(6, 21);
		g23.removeEdge(9, 11);
		
		HEADER("============================================================================");
		if (errors == 0) {
			HEADER("NICE JOB, THERE'S LIKE A 99% YOU'LL GET FULL MARKS FOR THE GRAPH SECTION");
		} else {
			HEADER("YOU STILL HAVE " + errors + " ERRORS.");
		}
		HEADER("============================================================================");
	}
	
	public static void print(Object o) {
		if (o instanceof Boolean && !((Boolean) o).booleanValue()) {
			errors++;
		}
		System.out.println(o);
	}
	
	public static void HEADER(String msg) {
		System.out.println("\n" + msg);
	}
}
