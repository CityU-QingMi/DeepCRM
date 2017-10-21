    @Test
    public void testPath()
    {

        Path path = new Path();

        Assert.assertEquals(0, path.nodes());
        Assert.assertEquals(null,path.firstNode());
        Assert.assertEquals(null,path.lastNode());

        path.add(new Edge(nodeA ,nodeB));
        Assert.assertEquals(2,path.nodes());
        Assert.assertEquals(nodeA,path.firstNode());
        Assert.assertEquals(nodeB,path.lastNode());

        path.add(new Edge(nodeB ,nodeC));
        Assert.assertEquals(3,path.nodes());
        Assert.assertEquals(nodeA,path.firstNode());
        Assert.assertEquals(nodeC,path.lastNode());
    }
