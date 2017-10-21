    @Test
    public void testDouble() throws Exception
    {
        Request request = new Request(null,null);
        Response response = new Response(null,null);
        
        TestHandler handler0 = new TestHandler("0");
        OtherHandler handlerA = new OtherHandler("A");
        TestHandler handler1 = new TestHandler("1");
        OtherHandler handlerB = new OtherHandler("B");
        handler0.setServer(new Server());
        handlerA.setServer(handler0.getServer());
        handler1.setServer(handler0.getServer());
        handlerB.setServer(handler0.getServer());
        handler0.setHandler(handlerA);
        handlerA.setHandler(handler1);
        handler1.setHandler(handlerB);
        handler0.start();
        handler0.handle("target",request,request,response);
        handler0.stop();
        String history=_history.toString();
        assertEquals(">S0>S1>W0>HA>W1>HB<HB<W1<HA<W0<S1<S0",history);
    }
