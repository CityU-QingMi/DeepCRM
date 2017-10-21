    @Test
    public void testLogNameFromDisplayName() throws Exception
    {
        ContextHandler handler = new ContextHandler();
        handler.setServer(new Server());
        handler.setDisplayName("An Interesting Project: app.tast.ic");
        try
        {
            handler.start();
            assertThat("handler.get", handler.getLogger().getName(), is(ContextHandler.class.getName() + ".An_Interesting_Project__app_tast_ic"));
        }
        finally
        {
            handler.stop();
        }
    }
