    @Test
    public void testOpenedClosedAreInvoked() throws Exception
    {
        initConnector(null);

        final CountDownLatch openedLatch = new CountDownLatch(1);
        final CountDownLatch closedLatch = new CountDownLatch(1);
        connector.addNetworkTrafficListener(new NetworkTrafficListener.Adapter()
        {
            public volatile Socket socket;

            @Override
            public void opened(Socket socket)
            {
                this.socket = socket;
                openedLatch.countDown();
            }

            @Override
            public void closed(Socket socket)
            {
                if (this.socket == socket)
                    closedLatch.countDown();
            }
        });
        int port = connector.getLocalPort();

        // Connect to the server
        Socket socket = new Socket("localhost", port);
        assertTrue(openedLatch.await(10, TimeUnit.SECONDS));

        socket.close();
        assertTrue(closedLatch.await(10, TimeUnit.SECONDS));
    }
