package treewidth;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
	
	private Graph graph;
	private TreeDecompositionGraph tree;
	
	public Algorithm(Graph graph, TreeDecompositionGraph tree) {
		this.graph = graph;
		this.tree = tree;
	}
	
	public int findMaxIndependentSet() {
		Bag rootBag = tree.getRoot();
		if(rootBag == null) {
			return -1;
		}
		System.out.println("Root bag is: " + rootBag.toString());
		visitBag(rootBag);
		return rootBag.getMaxIndependentSet();
	}
	
	private void visitBag(Bag bag) {
		for(Bag child : bag.getChildren()) {
			visitBag(child);
		}
		
		//if bag is a leaf in the tree
		if(bag.hasNoChildren()) {
			
			//For each independent set in bag
			for( NodeSet IS : bag.getIndependentSets() ) {
				bag.setFt(IS, IS.size());
			}
			
		}
		
		//if bag is not a leaf in the tree
		else {
			
			//for each independent U set of bag t
			for( NodeSet IS : bag.getIndependentSets() ) {
				NodeSet U = (NodeSet) IS.clone();
				
				//create ftU variable and add the current size;
				int ftU = 0;
				int weight_U = U.size();
				ftU += weight_U;
				
				NodeSet Vt = bag.getNodes();
				
				//for each child i
				for( Bag i : bag.getChildren() ) {
					
					NodeSet Vti = i.getNodes();
					NodeSet U_Vti = U.intersection(Vti);
					
					//construct an array of potential weights so we later can find the maximum
					ArrayList<Integer> potentialSets = new ArrayList<Integer>();
					
					//for each Ui
					for( NodeSet Ui : i.getIndependentSets() ) {
						
						//Add Ui as potential
						NodeSet Ui_Vt = Ui.intersection(Vt);
						if(Ui_Vt.equals(U_Vti)) {
							NodeSet Ui_U = Ui.intersection(U);
							int weight_Ui_U = Ui_U.size();
							int ftiUi = i.getFt(Ui);
							potentialSets.add(ftiUi - weight_Ui_U);
						}
					}
					
					//Add the largest IS of child to total sum of ftU
					if(potentialSets.size() > 0) {
						ftU += Collections.max(potentialSets);						
					}
					
				}
				
				bag.setFt(IS, ftU);
				
			}
			
		}
		
	}
	

}
