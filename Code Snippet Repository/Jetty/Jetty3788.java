    @Test
    public void testSimpleTriple() throws Exception
    {
        TestHandler handler0 = new TestHandler("0");
        TestHandler handler1 = new TestHandler("1");
        TestHandler handler2 = new TestHandler("2");
        handler0.setServer(new Server());
        handler1.setServer(handler0.getServer());
        handler2.setServer(handler0.getServer());
        handler0.setHandler(handler1);
        handler1.setHandler(handler2);
        handler0.start();
        handler0.handle("target",null,null,null);
        handler0.stop();
        String history=_history.toString();
        assertEquals(">S0>S1>S2>W0>W1>W2<W2<W1<W0<S2<S1<S0",history);
    }
