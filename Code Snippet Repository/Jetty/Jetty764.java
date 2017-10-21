    public Set<Edge> findEdgesFrom(Node from)
    {
        Set<Edge> fromedges = new HashSet<Edge>();

        for (Edge edge : this._edges)
        {
            if (edge.getFrom() == from)
            {
                fromedges.add(edge);
            }
        }

        return fromedges;
    }
