    public void assertNoWebAppContexts()
    {
        List<WebAppContext> contexts = getWebAppContexts();
        if (contexts.size() > 0)
        {
            for (WebAppContext context : contexts)
            {
                System.err.println("WebAppContext should not exist:\n" + context);
            }
            Assert.assertEquals("Contexts.size",0,contexts.size());
        }
    }
