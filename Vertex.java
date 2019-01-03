import java.util.List;

/**
 * Created by codecadet on 14/12/2018.
 */
public class Vertex {

    private PositionVect position;
    private List<Vertex> neighbours;
    private List<PositionVect> neighboursPositions;
    private List<PositionVect> freeNeighboursPositions;
    private List<PositionVect> inMazeNeighboursPositions;
    private Vertex vertexFather;
    private String imageURL;

    public PositionVect getPosition() {
        return position;
    }

    public void setPosition(PositionVect position) {
        this.position = position;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<PositionVect> getNeighboursPositions() {
        return neighboursPositions;
    }

    public void setNeighboursPositions(List<PositionVect> neighboursPositions) {
        this.neighboursPositions = neighboursPositions;
    }

    public void setFreeNeighboursPositions(List<PositionVect> freeNeighboursPositions) {
        this.freeNeighboursPositions = freeNeighboursPositions;
    }

    public List<PositionVect> getFreeNeighboursPositions() {
        return freeNeighboursPositions;
    }

    public List<PositionVect> getInMazeNeighboursPositions() {
        return inMazeNeighboursPositions;
    }

    public void setInMazeNeighboursPositions(List<PositionVect> inMazeNeighboursPositions) {
        this.inMazeNeighboursPositions = inMazeNeighboursPositions;
    }

    public Vertex getVertexFather() {
        return vertexFather;
    }

    public void setVertexFather(Vertex vertexFather) {
        this.vertexFather = vertexFather;
    }

    private String listToString (List<PositionVect> list) {
        String output = "{";
        for (PositionVect position: list) {
            output += "[" + position.getLeft() + "," + position.getRight() + "]";
        }
        output += "}";
        return output;
    }

    @Override
    public String toString() {
        return "Position: (" + position.getLeft() + "," + position.getRight() + ")  Neighbours: " + listToString(neighboursPositions)
                + "   Free Neighbours: " + listToString(freeNeighboursPositions)
                + "   Maze Neighbours: " + listToString(inMazeNeighboursPositions);
    }
}
