import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
public class PathFind extends EtherWorldActor
{
    //int nodeMultiplier = 50;
    double sqrt2 = 1.41421356237;
    List<node> openSet = new ArrayList();
    List<node> closedSet = new ArrayList();
    List<node> pathSet = new ArrayList();
    double goalX;
    double goalY;
    node bestNode = null;
    
    public void addedToEtherworld(){}
    
    public List<coord> FindPath(coord Start, coord Goal)
    {
        return FindPath(Start.getX(), Start.getY(), Goal.getX(), Goal.getY());
    }
    
    public List<coord> FindPath(double startX, double startY, double goalX, double goalY)
    {
        this.goalX = goalX;
        this.goalY = goalY;
        openSet.clear();
        closedSet.clear();
        pathSet.clear();
        node startNode = new node((int)startX, (int)startY, Math.abs(startX-goalX) + Math.abs(startY-goalY));
        openSet.add(startNode);
        bestNode = startNode;
        //double gScore = 0;
        //double fScore = gScore + abs(startX-goalX) + abs(startY-goalY);
        //abs(currentX-targetX) + abs(currentY-targetY)
        double bestF = Double.POSITIVE_INFINITY;
        while(!openSet.isEmpty())
        {
            for(int i = 0; i < openSet.size(); i++)
            {
                node current = openSet.get(i);
                double f = current.fScore;
                //double f = current.gScore + manhattanHeur(current.x, current.y, goalX, goalY);
                if(f < bestF)
                {
                    bestF = f;
                    bestNode = current;
                }
            }
            if(bestNode.x - goalX < 1 && bestNode.y - goalY < 1)
            {
                return tracePath(bestNode);
            }
            List<node> tmpNodes = new ArrayList();
            while(true)
            {
            node tmpNode = new node(bestNode.x + 1, bestNode.y, manhattanHeur(bestNode.x + 1, bestNode.y, goalX, goalY), bestNode.gScore + 1, bestNode);
            int val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            tmpNode = new node(bestNode.x + 1, bestNode.y + 1, manhattanHeur(bestNode.x + 1, bestNode.y + 1, goalX, goalY), bestNode.gScore + sqrt2, bestNode);
            val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            tmpNode = new node(bestNode.x + 1, bestNode.y - 1, manhattanHeur(bestNode.x + 1, bestNode.y - 1, goalX, goalY), bestNode.gScore + sqrt2, bestNode);
            val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            tmpNode = new node(bestNode.x, bestNode.y + 1, manhattanHeur(bestNode.x, bestNode.y + 1, goalX, goalY), bestNode.gScore + 1, bestNode);
            val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            tmpNode = new node(bestNode.x, bestNode.y - 1, manhattanHeur(bestNode.x, bestNode.y - 1, goalX, goalY), bestNode.gScore + 1, bestNode);
            val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            tmpNode = new node(bestNode.x - 1, bestNode.y, manhattanHeur(bestNode.x - 1, bestNode.y, goalX, goalY), bestNode.gScore + 1, bestNode);
            val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            tmpNode = new node(bestNode.x - 1, bestNode.y + 1, manhattanHeur(bestNode.x - 1, bestNode.y + 1, goalX, goalY), bestNode.gScore + sqrt2, bestNode);
            val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            tmpNode = new node(bestNode.x - 1, bestNode.y + 1, manhattanHeur(bestNode.x - 1, bestNode.y + 1, goalX, goalY), bestNode.gScore + sqrt2, bestNode);
            val = checkNode(tmpNode);
            if(val == 0) { openSet.add(tmpNode); }
            else if(val == 1) { break; }
            break;
            }
            closedSet.add(bestNode);
            openSet.remove(bestNode);
            /*for(int o = 0; o < openSet.size(); o++)
            {
                for(int c = 0; c < closedSet.size(); c++)
                {
                    if(openSet.get(o).equalTo(closedSet.get(c)))
                    {
                        openSet.remove(o);
                    }
                }
            }*/
            System.out.println(openSet.size());
        }
        return new ArrayList();
    }
    
    private List<coord> tracePath(node n)
    {
        List<coord> path = new ArrayList();
        node current = n;
        path.add(0, n.asCoord());
        while(n.parent != null)
        {
            current = current.parent;
            path.add(0, current.asCoord());
        }
        return path;
    }
    
    private int checkNode(node n)
    {
        List tiles = getTilesAt(n.x, n.y);
        if(!isTilePassable(n.x, n.y))
        {
            return 2;
        }
        for(int c = 0; c < closedSet.size(); c++)
        {
            if(n.equalTo(closedSet.get(c)))
            {
                return 2;
            }
        }
        for(int i = 0; i < openSet.size(); i++)
        {
            node current = openSet.get(i);
            if(current.x == n.x + 1 && current.y == n.y
                || current.x == n.x + 1 && current.y == n.y + 1
                || current.x == n.x + 1 && current.y == n.y - 1
                || current.x == n.x && current.y == n.y + 1
                || current.x == n.x && current.y == n.y - 1
                || current.x == n.x - 1 && current.y == n.y + 1
                || current.x == n.x - 1 && current.y == n.y 
                || current.x == n.x - 1 && current.y == n.y - 1)
                {
                    if(n.gScore >  current.gScore)
                    {
                        current.setParent(n.parent);
                        current.recalculate();
                        bestNode = current;
                        return 0;
                    }
                }
        }
        return 0;
    }
    
    private boolean isTilePassable(double x, double y)
    {
        List tiles = getTilesAt(x, y);
        for(int i = 0; i < tiles.size(); i++)
        {
            if(tiles.get(i) instanceof NonpassableTile)
            {
                return false;
            }
        }
        return true;
    }
    
    private double manhattanHeur(double x1, double y1, double x2, double y2)
    {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
    
    public class node
    {
        double x;
        double y;
        double gScore;
        double fScore;
        node parent;
        public node(double x, double y, double fScore, double gScore, node parent)
        {
            this.x = x;
            this.y = y;
            this.gScore = gScore;
            this.fScore = gScore + manhattanHeur(x, y, goalX, goalY);
            this.parent = parent;
        }
        
        public node(double x, double y, double gScore)
        {
            this.x = x;
            this.y = y;
            this.gScore = gScore;
            this.parent = null;
        }
        
        public coord asCoord()
        {
            return new coord(x, y);
        }
        
        public boolean equalTo(node n)
        {
            if(n.x == x && n.y == y) return true;
            return false;
        }
        
        public void recalculate()
        {
            if(parent != null)
            {
                if(parent.x + 1 == x && parent.y + 1 == y
                || parent.x + 1 == x && parent.y - 1 == y
                || parent.x - 1 == x && parent.y + 1 == y
                || parent.x - 1 == x && parent.y - 1 == y)
                {
                    gScore = parent.gScore + sqrt2;
                }
                else{
                    gScore = parent.gScore + 1;
                }
                fScore = gScore + manhattanHeur(x, y, goalX, goalY);
            }
        }
        
        public void setG(double gScore)
        {
            this.gScore = gScore;
        }
        
        public void setParent(node n)
        {
            parent = n;
        }
    }
}
