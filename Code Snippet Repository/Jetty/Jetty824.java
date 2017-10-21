    public void assertWebAppContextsExists(String... expectedContextPaths)
    {
        List<WebAppContext> contexts = getWebAppContexts();
        if (expectedContextPaths.length != contexts.size())
        {
            System.err.println("## Expected Contexts");
            for (String expected : expectedContextPaths)
            {
                System.err.println(expected);
            }
            System.err.println("## Actual Contexts");
            for (WebAppContext context : contexts)
            {
                System.err.printf("%s ## %s%n",context.getContextPath(),context);
            }
            Assert.assertEquals("Contexts.size",expectedContextPaths.length,contexts.size());
        }

        for (String expectedPath : expectedContextPaths)
        {
            boolean found = false;
            for (WebAppContext context : contexts)
            {
                if (context.getContextPath().equals(expectedPath))
                {
                    found = true;
                    Assert.assertThat("Context[" + context.getContextPath() + "].state", context.getState(), is("STARTED"));
                    break;
                }
            }
            Assert.assertTrue("Did not find Expected Context Path " + expectedPath,found);
        }
    }
