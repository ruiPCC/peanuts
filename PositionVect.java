/**
 * Created by codecadet on 13/12/2018.
 */
public class PositionVect {

    private int left;
    private int right;

    public PositionVect(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionVect)) return false;

        PositionVect position = (PositionVect) o;

        if (left != position.left) return false;
        return right == position.right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
