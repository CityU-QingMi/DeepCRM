    @Test
    public void testClose() throws Exception
    {
        for (int i=0;i<5;i++)
        {
            Thread.sleep(100);
            _timeout.notIdle();
        }
        _timeout.onClose();
        Thread.sleep(1500);
        Assert.assertNull(_expired);
    }
