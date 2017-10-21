    @Test
    public void testWrongPreface() throws Exception
    {
        final CountDownLatch latch = new CountDownLatch(1);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public void onFailure(Session session, Throwable failure)
            {
                latch.countDown();
            }
        });

        try (Socket socket = new Socket("localhost", connector.getLocalPort()))
        {
            // Preface starts with byte 0x50, send something different.
            OutputStream output = socket.getOutputStream();
            output.write(0x0);
            output.flush();

            Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));

            // The server will reply with a GOAWAY frame, and then shutdown.
            // Read until EOF.
            socket.setSoTimeout(1000);
            InputStream input = socket.getInputStream();
            while (true)
            {
                if (input.read() < 0)
                    break;
            }
        }
    }
