    public void addEdge(Edge edge)
    {
        Node fromNode = getNodeByName(edge.getFrom().getName());
        if (fromNode==null)
            addNode(fromNode=edge.getFrom());
        Node toNode = getNodeByName(edge.getTo().getName());
        if (toNode==null)
            addNode(toNode=edge.getTo());
        
        // replace edge with normalized edge
        if (edge.getFrom()!=fromNode || edge.getTo()!=toNode)
            edge=new Edge(fromNode,toNode);
        
        this._edges.add(edge);
    }
