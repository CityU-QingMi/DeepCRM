    @Ignore ("")
    @Test
    public void testLowOnMemory() throws Exception
    {
        _lowResourcesMonitor.setMaxMemory(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()+(100*1024*1024));
        Thread.sleep(1200);
        Assert.assertFalse(_lowResourcesMonitor.isLowOnResources());

        byte[] data = new byte[100*1024*1024];
        Arrays.fill(data,(byte)1);
        int hash = Arrays.hashCode(data);
        assertThat(hash,not(equalTo(0)));

        Thread.sleep(1200);
        Assert.assertTrue(_lowResourcesMonitor.isLowOnResources());
        data=null;
        System.gc();
        System.gc();

        Thread.sleep(1200);
        Assert.assertFalse(_lowResourcesMonitor.isLowOnResources());   
    }
