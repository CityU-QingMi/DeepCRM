    public void insertNode(Edge edge, String nodeName)
    {
        Node node = getNodeByName(nodeName);
        if (node==null)
        {
            node = new Node(nodeName);
        }

        insertNode(edge,node);
    }
