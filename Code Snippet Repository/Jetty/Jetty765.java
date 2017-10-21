    public Path getPath(String nodeNameOrigin, String nodeNameDest)
    {
        if (nodeNameOrigin.equals(nodeNameDest))
        {
            return new Path();
        }

        Node from = getNodeByName(nodeNameOrigin);
        Node to = getNodeByName(nodeNameDest);
        return getPath(from,to);
    }
