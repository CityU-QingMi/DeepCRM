    public void addEdge(String from, String to)
    {
        Node fromNode = getNodeByName(from);
        if (fromNode==null)
        {
            fromNode = new Node(from);
            addNode(fromNode);
        }
            
        Node toNode = getNodeByName(to);
        if (toNode==null)
        {
            toNode = new Node(to);
            addNode(toNode);
        }

        addEdge(fromNode,toNode);
    }
