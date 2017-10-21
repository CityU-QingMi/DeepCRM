    @Test
    public void testNotIdle() throws Exception
    {
        for (int i=0;i<20;i++)
        {
            Thread.sleep(100);
            _timeout.notIdle();
        }

        Assert.assertNull(_expired);
    }
