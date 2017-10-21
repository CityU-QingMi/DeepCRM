    @Test
    public void testTriangleDirected()
    {
        Graph graph = new Graph();
        graph.addEdge(new Edge(nodeA,nodeB));
        graph.addEdge(new Edge(nodeA,nodeC));
        graph.addEdge(new Edge(nodeB,nodeC));
        Assert.assertEquals(3,graph.getNodes().size());
        Assert.assertEquals(3,graph.getEdges().size());
        Path path = graph.getPath(nodeA,nodeB);
        Assert.assertEquals(2,path.nodes());
        path = graph.getPath(nodeA,nodeC);
        Assert.assertEquals(2,path.nodes());
        path = graph.getPath(nodeB,nodeC);
        Assert.assertEquals(2,path.nodes());

    }
