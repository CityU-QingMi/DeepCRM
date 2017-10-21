    @Test
    public void testPing() throws Exception
    {
        start(new ServerSessionListener.Adapter());

        final byte[] payload = new byte[8];
        new Random().nextBytes(payload);
        final CountDownLatch latch = new CountDownLatch(1);
        Session session = newClient(new Session.Listener.Adapter()
        {
            @Override
            public void onPing(Session session, PingFrame frame)
            {
                Assert.assertTrue(frame.isReply());
                Assert.assertArrayEquals(payload, frame.getPayload());
                latch.countDown();
            }
        });

        PingFrame frame = new PingFrame(payload, false);
        session.ping(frame, Callback.NOOP);

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
