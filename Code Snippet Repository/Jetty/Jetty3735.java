    @Test
    public void testContextVirtualGetContext() throws Exception
    {
        Server server = new Server();
        LocalConnector connector = new LocalConnector(server);
        server.setConnectors(new Connector[] { connector });
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        ContextHandler rootA = new ContextHandler(contexts,"/");
        rootA.setVirtualHosts(new String[] {"a.com"});

        ContextHandler rootB = new ContextHandler(contexts,"/");
        rootB.setVirtualHosts(new String[] {"b.com"});

        ContextHandler rootC = new ContextHandler(contexts,"/");
        rootC.setVirtualHosts(new String[] {"c.com"});


        ContextHandler fooA = new ContextHandler(contexts,"/foo");
        fooA.setVirtualHosts(new String[] {"a.com"});

        ContextHandler fooB = new ContextHandler(contexts,"/foo");
        fooB.setVirtualHosts(new String[] {"b.com"});


        ContextHandler foobarA = new ContextHandler(contexts,"/foo/bar");
        foobarA.setVirtualHosts(new String[] {"a.com"});

        server.start();

        // System.err.println(server.dump());

        Assert.assertEquals(rootA._scontext, rootA._scontext.getContext("/"));
        Assert.assertEquals(fooA._scontext, rootA._scontext.getContext("/foo"));
        Assert.assertEquals(foobarA._scontext, rootA._scontext.getContext("/foo/bar"));
        Assert.assertEquals(foobarA._scontext, rootA._scontext.getContext("/foo/bar/bob"));

        Assert.assertEquals(rootA._scontext, rootA._scontext.getContext("/other"));
        Assert.assertEquals(rootB._scontext, rootB._scontext.getContext("/other"));
        Assert.assertEquals(rootC._scontext, rootC._scontext.getContext("/other"));

        Assert.assertEquals(fooB._scontext, rootB._scontext.getContext("/foo/other"));
        Assert.assertEquals(rootC._scontext, rootC._scontext.getContext("/foo/other"));
    }
