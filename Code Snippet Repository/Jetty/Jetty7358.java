    @Test
    public void testConnectionListener() throws Exception
    {
        start(new EmptyServerHandler());

        CountDownLatch openLatch = new CountDownLatch(1);
        CountDownLatch closeLatch = new CountDownLatch(1);
        client.addBean(new org.eclipse.jetty.io.Connection.Listener()
        {
            @Override
            public void onOpened(org.eclipse.jetty.io.Connection connection)
            {
                openLatch.countDown();
            }

            @Override
            public void onClosed(org.eclipse.jetty.io.Connection connection)
            {
                closeLatch.countDown();
            }
        });

        long idleTimeout = 1000;
        client.setIdleTimeout(idleTimeout);

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertTrue(openLatch.await(1, TimeUnit.SECONDS));

        Thread.sleep(2 * idleTimeout);
        Assert.assertTrue(closeLatch.await(1, TimeUnit.SECONDS));
    }
