    public void add(Edge edge)
    {
        _edges.add(edge);
        if (_nodes.size() == 0)
        {
            _nodes.add(edge.getFrom());
        }
        else
        {
            assert _nodes.get(_nodes.size() - 1).equals(edge.getFrom());
        }
        _nodes.add(edge.getTo());
    }
