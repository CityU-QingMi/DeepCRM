    @Test
    public void testIdle() throws Exception
    {
        for (int i=0;i<5;i++)
        {
            Thread.sleep(100);
            _timeout.notIdle();
        }
        Thread.sleep(1500);
        Assert.assertNotNull(_expired);
    }
