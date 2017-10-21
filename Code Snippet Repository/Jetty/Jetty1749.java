    @Test
    public void testLengthen() throws Exception
    {
        for (int i=0;i<5;i++)
        {
            Thread.sleep(100);
            _timeout.notIdle();
        }
        _timeout.setIdleTimeout(10000);
        Thread.sleep(1500);
        Assert.assertNull(_expired);
    }
