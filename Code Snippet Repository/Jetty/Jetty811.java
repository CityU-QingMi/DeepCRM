    @Test
    public void testSquareCyclic()
    {
        Graph graph = new Graph();
        graph.addEdge(new Edge(nodeA,nodeB));
        graph.addEdge(new Edge(nodeB,nodeC));
        graph.addEdge(new Edge(nodeC,nodeD));
        graph.addEdge(new Edge(nodeD,nodeA));
        Assert.assertEquals(4,graph.getNodes().size());
        Assert.assertEquals(4,graph.getEdges().size());
        Path path = graph.getPath(nodeA,nodeB);
        Assert.assertEquals(2,path.nodes());

        path = graph.getPath(nodeA,nodeC);
        Assert.assertEquals(3,path.nodes());
        path = graph.getPath(nodeA,nodeD);
        Assert.assertEquals(4,path.nodes());

        graph.addNode(nodeE);
        path = graph.getPath(nodeA,nodeE);
        Assert.assertEquals(null,path);
    }
