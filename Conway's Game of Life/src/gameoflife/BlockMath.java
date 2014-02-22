package gameoflife;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BlockMath {
	
	public Set<BlockPoint> moveBlocks(Set<BlockPoint> blocks) {
		Set<BlockPoint> remainingBlocks = new HashSet<BlockPoint>();
		Set<BlockPoint> deadCells = new HashSet<BlockPoint>();
		
		for (BlockPoint block : blocks) {
			int i = 0;
			for (int x = -1; x < 2 ; x++) {
				for (int y = -1 ; y < 2 ; y++) {
					int x1 = block.getX() + x;
					int y1 = block.getY() + y;
					if (block.getX() + x == -1) {
						x1 = 59;
					}
					if (block.getY() + y == -1) {
						y1 = 59;
					}
					if (block.getX() + x == 60) {
						x1 = 0;
					}
					if (block.getY() + y == 60) {
						y1 = 0;
					}
					if (blocks.contains(new BlockPoint(x1, y1))) {
						i++;
					} else {
						deadCells.add(new BlockPoint(x1, y1));
					}
				}
			}
			if (i == 3 || i == 4) {
				remainingBlocks.add(block);
			}
			
			
		}

		remainingBlocks.addAll(findNeighbors(blocks, deadCells));
		return remainingBlocks;
		
	}

	private Collection<? extends BlockPoint> findNeighbors(
			Set<BlockPoint> blocks, Set<BlockPoint> deadCells) {
		Set<BlockPoint> revivedNeighbours = new HashSet<BlockPoint>();
		for (BlockPoint block : deadCells) {
			int i = 0;
			for (int x = -1 ; x < 2 ; x++) {
				for (int y = -1 ; y < 2; y++) {
					int x1 = block.getX() + x;
					int y1 = block.getY() + y;
					if (block.getX() + x == -1) {
						x1 = 59;
					}
					if (block.getY() + y == -1) {
						y1 = 59;
					}
					if (block.getX() + x == 60) {
						x1 = 0;
					}
					if (block.getY() + y == 60) {
						y1 = 0;
					}
					if (blocks.contains(new BlockPoint(x1, y1))) {
						i++;
					}
					
				}
			}
			if (i == 3) {
				revivedNeighbours.add(block);
			}
			
		}
		return revivedNeighbours;
	}

}
