    @Test
    public void testPoint()
    {
        Graph graph = new Graph();
        graph.addNode(nodeA);
        Assert.assertEquals(1,graph.getNodes().size());
        Assert.assertEquals(0,graph.getEdges().size());
        Path path = graph.getPath(nodeA,nodeA);
        Assert.assertEquals(0,path.nodes());
    }
