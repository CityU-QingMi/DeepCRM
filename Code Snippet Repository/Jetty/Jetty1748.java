    @Test
    public void testShorten() throws Exception
    {
        for (int i=0;i<5;i++)
        {
            Thread.sleep(100);
            _timeout.notIdle();
        }
        _timeout.setIdleTimeout(100);
        Thread.sleep(400);
        Assert.assertNotNull(_expired);
    }
