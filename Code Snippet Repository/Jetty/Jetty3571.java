    @Test
    public void testMaxConnectionsAndMaxIdleTime() throws Exception
    {
        _lowResourcesMonitor.setMaxMemory(0);
        Assert.assertFalse(_lowResourcesMonitor.isLowOnResources());

        Socket[] socket = new Socket[_lowResourcesMonitor.getMaxConnections()+1];
        for (int i=0;i<socket.length;i++)
            socket[i]=new Socket("localhost",_connector.getLocalPort());
        
        Thread.sleep(1200);
        Assert.assertTrue(_lowResourcesMonitor.isLowOnResources());

        try(Socket newSocket = new Socket("localhost",_connector.getLocalPort()))
        {
            // wait for low idle time to close sockets, but not new Socket
            Thread.sleep(1200);
            Assert.assertFalse(_lowResourcesMonitor.isLowOnResources());   

            for (int i=0;i<socket.length;i++)
                Assert.assertEquals(-1,socket[i].getInputStream().read());

            newSocket.getOutputStream().write("GET / HTTP/1.0\r\n\r\n".getBytes(StandardCharsets.UTF_8));
            Assert.assertEquals('H',newSocket.getInputStream().read());
        }
    }
