    @Test
    public void testLogNameFromContextPath_Empty() throws Exception
    {
        ContextHandler handler = new ContextHandler();
        handler.setServer(new Server());
        handler.setContextPath("");
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
