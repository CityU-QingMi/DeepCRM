    @Test
    public void testContextGetContext() throws Exception
    {
        Server server = new Server();
        LocalConnector connector = new LocalConnector(server);
        server.setConnectors(new Connector[] { connector });
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        ContextHandler rootA = new ContextHandler(contexts,"/");
        ContextHandler fooA = new ContextHandler(contexts,"/foo");
        ContextHandler foobarA = new ContextHandler(contexts,"/foo/bar");

        server.start();

        // System.err.println(server.dump());

        Assert.assertEquals(rootA._scontext, rootA._scontext.getContext("/"));
        Assert.assertEquals(fooA._scontext, rootA._scontext.getContext("/foo"));
        Assert.assertEquals(foobarA._scontext, rootA._scontext.getContext("/foo/bar"));
        Assert.assertEquals(foobarA._scontext, rootA._scontext.getContext("/foo/bar/bob.jsp"));
        Assert.assertEquals(rootA._scontext, rootA._scontext.getContext("/other"));
        Assert.assertEquals(fooA._scontext, rootA._scontext.getContext("/foo/other"));

        Assert.assertEquals(rootA._scontext, foobarA._scontext.getContext("/"));
        Assert.assertEquals(fooA._scontext, foobarA._scontext.getContext("/foo"));
        Assert.assertEquals(foobarA._scontext, foobarA._scontext.getContext("/foo/bar"));
        Assert.assertEquals(foobarA._scontext, foobarA._scontext.getContext("/foo/bar/bob.jsp"));
        Assert.assertEquals(rootA._scontext, foobarA._scontext.getContext("/other"));
        Assert.assertEquals(fooA._scontext, foobarA._scontext.getContext("/foo/other"));
    }
