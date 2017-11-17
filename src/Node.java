
import java.util.ArrayList;
import java.util.List;

public class Node<T>{
	private Piece[][] currentBoard;
	private int points;
	private Node<T> parent;
	private ArrayList<Node<T>> child;

	public Node(Piece[][] cB,Node<T> p,int pts){
		currentBoard=cB;
		points=pts;
		parent=p;
		child=new ArrayList<Node<T>>();
	}

	public Piece[][] getBoard(){
		return currentBoard;
	}
	public int getPoints(){
		return points;
	}
	public Node<T> getParent(){
		return parent;
	}
	public List<Node<T>> getChildren(){
		return child;
	}
	public void setParent(Node<T> p){
		parent=p;
	}
	public void addChild(Node<T> c){
		child.add(c);
	}

	public boolean isTerminal(){
		if(child==null){
			return true;
		}
		return false;

	}
}

