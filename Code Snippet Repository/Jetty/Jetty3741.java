    @Test
    public void testLogNameFromContextPath_Deep() throws Exception
    {
        ContextHandler handler = new ContextHandler();
        handler.setServer(new Server());
        handler.setContextPath("/app/tast/ic");
        try
        {
            handler.start();
            assertThat("handler.get", handler.getLogger().getName(), is(ContextHandler.class.getName() + ".app_tast_ic"));
        }
        finally
        {
            handler.stop();
        }
    }
