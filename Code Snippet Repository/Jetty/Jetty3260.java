    @Slow
    @Test
    public void testOpenClose() throws Exception
    {
        server.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                throw new IllegalStateException();
            }
        });
        server.start();

        final AtomicInteger callbacks = new AtomicInteger();
        final CountDownLatch openLatch = new CountDownLatch(1);
        final CountDownLatch closeLatch = new CountDownLatch(1);
        connector.addBean(new Connection.Listener.Adapter()
        {
            @Override
            public void onOpened(Connection connection)
            {
                callbacks.incrementAndGet();
                openLatch.countDown();
            }

            @Override
            public void onClosed(Connection connection)
            {
                callbacks.incrementAndGet();
                closeLatch.countDown();
            }
        });

        try (Socket socket = new Socket("localhost", connector.getLocalPort());)
        {
            socket.setSoTimeout((int)connector.getIdleTimeout());

            Assert.assertTrue(openLatch.await(5, TimeUnit.SECONDS));
            socket.shutdownOutput();
            Assert.assertTrue(closeLatch.await(5, TimeUnit.SECONDS));
            String response=IO.toString(socket.getInputStream());
            Assert.assertEquals(0,response.length());

            // Wait some time to see if the callbacks are called too many times
            TimeUnit.MILLISECONDS.sleep(200);
            Assert.assertEquals(2, callbacks.get());
        }
    }
