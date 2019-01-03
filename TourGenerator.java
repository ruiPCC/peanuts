


import java.util.LinkedList;
import java.util.List;


/**
 * Created by codecadet on 14/12/2018.
 */
public class TourGenerator {

    private final int GRID_SIZE = 7;
    private Vertex[] gridGraph;
    private List<Vertex> maze;
    private List<Vertex> tour;
    private String[][] pictureURL;
    private String solutionTour;

    public void constructGridGraph() {

        gridGraph = new Vertex[GRID_SIZE*GRID_SIZE];

        for (int i = 0; i < GRID_SIZE*GRID_SIZE; i++) {
            gridGraph[i] = new Vertex();
            gridGraph[i].setPosition(new PositionVect(i / GRID_SIZE, i % GRID_SIZE));
            gridGraph[i].setNeighboursPositions(new LinkedList<PositionVect>());
            gridGraph[i].setInMazeNeighboursPositions(new LinkedList<PositionVect>());
            if (i / GRID_SIZE > 0) {
                gridGraph[i].getNeighboursPositions().add(new PositionVect(i / GRID_SIZE -1, i % GRID_SIZE));
            }
            if (i % GRID_SIZE > 0) {
                gridGraph[i].getNeighboursPositions().add(new PositionVect(i / GRID_SIZE, i % GRID_SIZE -1));
            }
            if (i / GRID_SIZE < GRID_SIZE-1) {
                gridGraph[i].getNeighboursPositions().add(new PositionVect(i / GRID_SIZE +1, i % GRID_SIZE));
            }
            if (i % GRID_SIZE < GRID_SIZE-1) {
                gridGraph[i].getNeighboursPositions().add(new PositionVect(i / GRID_SIZE, i % GRID_SIZE +1));
            }
            gridGraph[i].setFreeNeighboursPositions(gridGraph[i].getNeighboursPositions());
            //gridGraphDraft[i].setLabel(i);
        }
        //return gridGraphDraft;
    }

    public void generateMaze() {
        constructGridGraph();
        maze = new LinkedList<Vertex>();
        maze.add(gridGraph[0]);
        //tour = new LinkedList<Vertex>();
        //tour.add(gridGraph[0]);
        updateFreeNeighbours(gridGraph,gridGraph[0]);
        while (maze.size() < gridGraph.length) {
            Vertex headVertex = chooseHeadVertex(maze);
            fowardVertex(gridGraph, maze, headVertex);
        }

        solutionTour = solution();

        //return tour;
    }

    private void fowardVertex(Vertex[] graph, List<Vertex> tour, Vertex headVertex) {

        int index = (int) (Math.random()*headVertex.getNeighboursPositions().size());
        Vertex newVertex = graph[headVertex.getNeighboursPositions().get(index).getLeft()*GRID_SIZE
                + headVertex.getNeighboursPositions().get(index).getRight()];
        headVertex.getInMazeNeighboursPositions().add(newVertex.getPosition());
        newVertex.getInMazeNeighboursPositions().add(headVertex.getPosition());
        newVertex.setVertexFather(headVertex);
        tour.add(newVertex);
        updateFreeNeighbours(graph, newVertex);
        if (newVertex.getPosition().equals(new PositionVect(GRID_SIZE-1,GRID_SIZE-1))) {
            generateTour(newVertex);
        }
    }

    private void generateTour(Vertex lastVertex) {
        tour = new LinkedList<Vertex>();
        Vertex vertex = lastVertex;
        tour.add(0, vertex);
        while (vertex.getVertexFather() != null) {
            vertex = vertex.getVertexFather();
            tour.add(0,vertex);
        }
    }

    private void updateFreeNeighbours(Vertex[] graph, Vertex newVertex) {
        for (Vertex vertex: graph) {
            vertex.getFreeNeighboursPositions().remove(newVertex.getPosition());
        }
    }

    private Vertex chooseHeadVertex(List<Vertex> tour) {
        int index = tour.size()-1;
        while (tour.get(index).getFreeNeighboursPositions().isEmpty() && index > -1) {
            index--;
        }
        return tour.get(index);
    }

    public String[][] createPictureURL() {
        pictureURL = new String[GRID_SIZE*3][GRID_SIZE*3];
        for (int i = 0; i < GRID_SIZE*3; i++) {
            for (int j = 0; j < GRID_SIZE*3; j++) {
                pictureURL[i][j] = (i%3==1 && j%3==1)?"X":" ";  //"<img width=2% height=3% src='https://image.nj.com/home/njo-media/width620/img/entertainment_impact/photo/facebooksquarejpg-ad6aa67583a46af2.jpg'/>":"<img width=2% height=3% src='https://image.nj.com/home/njo-media/width620/img/entertainment_impact/photo/facebooksquarejpg-ad6aa67583a46af2.jpg'/>";
            }
        }
        pictureURL[1][1] = "D";
        pictureURL[3*GRID_SIZE-2][3*GRID_SIZE-2] = "F";

        for (Vertex vertex: maze) {
            if (vertex.getPosition().getLeft() > 0 &&
                    vertex.getInMazeNeighboursPositions().contains(
                            new PositionVect(vertex.getPosition().getLeft()-1,vertex.getPosition().getRight()))) {
                pictureURL[vertex.getPosition().getLeft()*3][vertex.getPosition().getRight()*3+1] = "|";
            }
            if (vertex.getPosition().getLeft() < GRID_SIZE-1 &&
                    vertex.getInMazeNeighboursPositions().contains(
                            new PositionVect(vertex.getPosition().getLeft()+1,vertex.getPosition().getRight()))) {
                pictureURL[vertex.getPosition().getLeft()*3+2][vertex.getPosition().getRight()*3+1] = "|";
            }
            if (vertex.getPosition().getRight() > 0 &&
                    vertex.getInMazeNeighboursPositions().contains(
                            new PositionVect(vertex.getPosition().getLeft(),vertex.getPosition().getRight()-1))) {
                pictureURL[vertex.getPosition().getLeft()*3+1][vertex.getPosition().getRight()*3] = "_";
            }
            if (vertex.getPosition().getRight() < GRID_SIZE-1 &&
                    vertex.getInMazeNeighboursPositions().contains(
                            new PositionVect(vertex.getPosition().getLeft(),vertex.getPosition().getRight()+1))) {
                pictureURL[vertex.getPosition().getLeft()*3+1][vertex.getPosition().getRight()*3+2] = "_";
            }
        }

        return pictureURL;

    }

        public String printPictureURL() {
            createPictureURL();
            //String[] output = new String[GRID_SIZE];
            //"https://image.nj.com/home/njo-media/width620/img/entertainment_impact/photo/facebooksquarejpg-ad6aa67583a46af2.jpg";
            String output = "";
            for (String[] row: pictureURL) {
                String drawnRow = "";
                for (String cell: row) {
                    drawnRow += cell;//Character.toString((char) 164);
                }
                output += drawnRow + "\n";
                //System.out.println(drawnRow);
            }

            System.out.println(output);
            System.out.println(solutionTour);
            return output;
        }

    private String solution() {
        String solution = "";

        int counter = 0;


        while (counter < tour.size()-1) {

            if (tour.get(counter+1).getPosition().getLeft()>tour.get(counter).getPosition().getLeft()) {
                solution += "S";
            } else if (tour.get(counter+1).getPosition().getLeft()<tour.get(counter).getPosition().getLeft()) {
                solution += "N";
            } else if (tour.get(counter+1).getPosition().getRight()>tour.get(counter).getPosition().getRight()) {
                solution += "E";
            } else if (tour.get(counter+1).getPosition().getRight()<tour.get(counter).getPosition().getRight()) {
                solution += "W";
            }
            counter++;
        }


        solution += tour.size()-1;

        return solution;
    }

    public String[][] getPictureURL() {
        return pictureURL;
    }

    public Vertex[] getGridGraph() {
        return gridGraph;
    }

    public List<Vertex> getMaze() {
        return maze;
    }

    public List<Vertex> getTour() {
        return tour;
    }

}

