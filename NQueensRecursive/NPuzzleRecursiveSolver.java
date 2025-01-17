
public class NPuzzleRecursiveSolver {
	protected Board solutionBoard;
	
	Board solve(Board board) {
		initializeSearch();

		searchSolution(new Board(board));

		return solutionBoard;
	}

	void initializeSearch() {
		solutionBoard = null;
	}

	private void searchSolution(Board boardConfiguration) {
		if (boardConfiguration.isFull()) {
			solutionBoard = boardConfiguration;
			}
			
		    //we should add a queen in each empty column.
			Integer emptyCol = boardConfiguration.getFirstEmptyColumn();
			if (emptyCol != null) {
			   for (int y = 0; y < boardConfiguration.height; y++) {
			       if (!boardConfiguration.canAttackOtherQueens(emptyCol, y)) {
			           boardConfiguration.addQueen(emptyCol, y);
			           searchSolution(boardConfiguration);
			           
			           if (solutionBoard != null) {
			        	 //if there's a solution, return it.  
			             return;
			            }
			          //if there's no solution,keep trying.
			          boardConfiguration.removeQueen(emptyCol);
			       }
				}
			 }
	  }
  }
