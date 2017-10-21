    @Test
    public void testSingle() throws Exception
    {
        TestHandler handler0 = new TestHandler("0");
        handler0.setServer(new Server());
        handler0.start();
        handler0.handle("target",null,null,null);
        handler0.stop();
        String history=_history.toString();
        assertEquals(">S0>W0<W0<S0",history);
    }
