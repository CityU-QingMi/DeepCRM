    public Node getNodeByName(String name)
    {
        for (Node node : _nodes)
        {
            if (node.getName().equals(name))
            {
                return node;
            }
        }
        return null;
    }
