    public Set<Edge> findEdges(Node node)
    {
        Set<Edge> fromedges = new HashSet<Edge>();

        for (Edge edge : this._edges)
        {
            if ((edge.getFrom() == node) || (edge.getTo() == node))
            {
                fromedges.add(edge);
            }
        }

        return fromedges;
    }
