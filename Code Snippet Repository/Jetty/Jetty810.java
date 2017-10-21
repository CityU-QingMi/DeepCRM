    @Test
    public void testSquareDirected()
    {
        Graph graph = new Graph();
        graph.addEdge(new Edge(nodeA,nodeB));
        graph.addEdge(new Edge(nodeB,nodeC));
        graph.addEdge(new Edge(nodeA,nodeD));
        graph.addEdge(new Edge(nodeD,nodeC));
        Assert.assertEquals(4,graph.getNodes().size());
        Assert.assertEquals(4,graph.getEdges().size());
        Path path = graph.getPath(nodeA,nodeC);
        Assert.assertEquals(3,path.nodes());

        path = graph.getPath(nodeC,nodeA);
        Assert.assertEquals(null,path);

    }
