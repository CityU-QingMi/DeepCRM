    public void start() throws Exception
    {
        Assert.assertThat("No servlet defined yet.  Did you use #setContentServlet()?",tester,notNullValue());
        
        if (LOG.isDebugEnabled())
        {
            tester.dumpStdErr();
        }
        tester.start();
    }
