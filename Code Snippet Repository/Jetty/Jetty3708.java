    @Test
    public void testFindContainer() throws Exception
    {
        Server server = new Server();

        ContextHandler contextA = new ContextHandler("/a");
        IsHandledHandler handlerA = new IsHandledHandler("A");
        contextA.setHandler(handlerA);

        ContextHandler contextB = new ContextHandler("/b");
        IsHandledHandler handlerB = new IsHandledHandler("B");
        HandlerWrapper wrapperB = new HandlerWrapper();
        wrapperB.setHandler(handlerB);
        contextB.setHandler(wrapperB);

        ContextHandler contextC = new ContextHandler("/c");
        IsHandledHandler handlerC = new IsHandledHandler("C");
        contextC.setHandler(handlerC);

        ContextHandlerCollection collection = new ContextHandlerCollection();

        collection.addHandler(contextA);
        collection.addHandler(contextB);
        collection.addHandler(contextC);

        HandlerWrapper wrapper = new HandlerWrapper();
        wrapper.setHandler(collection);
        server.setHandler(wrapper);

        assertEquals(wrapper,AbstractHandlerContainer.findContainerOf(server,HandlerWrapper.class,handlerA));
        assertEquals(contextA,AbstractHandlerContainer.findContainerOf(server,ContextHandler.class,handlerA));
        assertEquals(contextB,AbstractHandlerContainer.findContainerOf(server,ContextHandler.class,handlerB));
        assertEquals(wrapper,AbstractHandlerContainer.findContainerOf(server,HandlerWrapper.class,handlerB));
        assertEquals(contextB,AbstractHandlerContainer.findContainerOf(collection,HandlerWrapper.class,handlerB));
        assertEquals(wrapperB,AbstractHandlerContainer.findContainerOf(contextB,HandlerWrapper.class,handlerB));

    }
