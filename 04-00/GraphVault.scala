/**
 * Defines a basics graph implementation technique and algorithms
 */
class Graph {
    class Node {
        var adjacentNodes: List[Node] = Nil
        def connectTo(node: Node) {
            if(adjacentNodes.find(node.equals).isEmpty) {
                adjacentNodes = node :: adjacentNodes
            }
        }
    }

    var nodes: List[Node] = Nil
    def newNode: Node = {
        val res = new Node
        nodes = res :: nodes
        res
    }
} 
