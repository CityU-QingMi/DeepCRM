    @Test
    public void testMaxLowResourceTime() throws Exception
    {
        _lowResourcesMonitor.setMaxLowResourcesTime(2000);
        Assert.assertFalse(_lowResourcesMonitor.isLowOnResources());

        try(Socket socket0 = new Socket("localhost",_connector.getLocalPort()))
        {
            _lowResourcesMonitor.setMaxMemory(1);

            Thread.sleep(1200);
            Assert.assertTrue(_lowResourcesMonitor.isLowOnResources());

            try(Socket socket1 = new Socket("localhost",_connector.getLocalPort()))
            {
                Thread.sleep(1200);
                Assert.assertTrue(_lowResourcesMonitor.isLowOnResources());
                Assert.assertEquals(-1,socket0.getInputStream().read());
                socket1.getOutputStream().write("G".getBytes(StandardCharsets.UTF_8));

                Thread.sleep(1200);
                Assert.assertTrue(_lowResourcesMonitor.isLowOnResources());
                socket1.getOutputStream().write("E".getBytes(StandardCharsets.UTF_8));

                Thread.sleep(1200);
                Assert.assertTrue(_lowResourcesMonitor.isLowOnResources());
                Assert.assertEquals(-1,socket1.getInputStream().read());
            }
        }
    }
