    @Test
    public void testTriple() throws Exception
    {
        Request request = new Request(null,null);
        Response response = new Response(null,null);
        
        TestHandler handler0 = new TestHandler("0");
        OtherHandler handlerA = new OtherHandler("A");
        TestHandler handler1 = new TestHandler("1");
        OtherHandler handlerB = new OtherHandler("B");
        TestHandler handler2 = new TestHandler("2");
        OtherHandler handlerC = new OtherHandler("C");
        handler0.setServer(new Server());
        handlerA.setServer(handler0.getServer());
        handler1.setServer(handler0.getServer());
        handlerB.setServer(handler0.getServer());
        handler2.setServer(handler0.getServer());
        handlerC.setServer(handler0.getServer());
        handler0.setHandler(handlerA);
        handlerA.setHandler(handler1);
        handler1.setHandler(handlerB);
        handlerB.setHandler(handler2);
        handler2.setHandler(handlerC);
        handler0.start();
        handler0.handle("target",request,request,response);
        handler0.stop();
        String history=_history.toString();
        assertEquals(">S0>S1>S2>W0>HA>W1>HB>W2>HC<HC<W2<HB<W1<HA<W0<S2<S1<S0",history);
    }
