    @Test
    public void testClientSendsGoAwayOnStop() throws Exception
    {
        CountDownLatch closeLatch = new CountDownLatch(1);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public void onClose(Session session, GoAwayFrame frame)
            {
                closeLatch.countDown();
            }
        });

        newClient(new Session.Listener.Adapter());

        sleep(1000);

        client.stop();

        Assert.assertTrue(closeLatch.await(5, TimeUnit.SECONDS));
    }
