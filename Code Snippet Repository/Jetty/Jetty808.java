    @Test
    public void testLine()
    {
        Graph graph = new Graph();
        graph.addEdge(new Edge(nodeA,nodeB));
        Assert.assertEquals(2,graph.getNodes().size());
        Assert.assertEquals(1,graph.getEdges().size());
        Path path = graph.getPath(nodeA,nodeB);
        Assert.assertEquals(2,path.nodes());
    }
