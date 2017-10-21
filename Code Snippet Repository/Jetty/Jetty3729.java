    @Test
    public void testLogNameFromContextPath_Undefined() throws Exception
    {
        ContextHandler handler = new ContextHandler();
        handler.setServer(new Server());
        try
        {
            handler.start();
            assertThat("handler.get", handler.getLogger().getName(), is(ContextHandler.class.getName() + ".ROOT"));
        }
        finally
        {
            handler.stop();
        }
    }
