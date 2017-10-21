    public void insertNode(Edge edge, Node node)
    {
        // Remove existing edge
        removeEdge(edge);
        // Ensure node is added
        addNode(node);
        // Add start edge
        addEdge(edge.getFrom(),node);
        // Add second edge
        addEdge(node,edge.getTo());
    }
