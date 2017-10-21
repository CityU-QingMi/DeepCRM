    @Test
    public void testServerSendsGoAwayOnStop() throws Exception
    {
        start(new ServerSessionListener.Adapter());

        CountDownLatch closeLatch = new CountDownLatch(1);
        newClient(new Session.Listener.Adapter()
        {
            @Override
            public void onClose(Session session, GoAwayFrame frame)
            {
                closeLatch.countDown();
            }
        });

        sleep(1000);

        server.stop();

        Assert.assertTrue(closeLatch.await(5, TimeUnit.SECONDS));
    }
