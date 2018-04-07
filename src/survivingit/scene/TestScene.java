package survivingit.scene;

import survivingit.gameobjects.*;
import survivingit.graph.AStar;
import survivingit.graph.ChebyshevDistance;

public class TestScene extends Scene {

    public TestScene() {
        super(32, 32);
        this.addPlayer(new Player(-1, -1));
        this.add(new Campfire(-3, -3));
        for(int x = 0; x < 32; x += 5) {
            for(int y = 0; y < 32; y += 5) {
                if(this.getTileAt(x, y).isPassable()) {
                    // Add fox if tile is walkable
                    this.add(new Fox(x, y));
                }
            }
        }
    }
}
