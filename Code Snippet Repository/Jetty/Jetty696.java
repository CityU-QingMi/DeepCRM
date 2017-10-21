        public SslBytesTest.SimpleProxy.AutomaticFlow startAutomaticFlow() throws InterruptedException
        {
            final CountDownLatch startLatch = new CountDownLatch(2);
            final CountDownLatch stopLatch = new CountDownLatch(2);
            Future<Object> clientToServer = threadPool.submit(() ->
            {
                startLatch.countDown();
                logger.debug("Automatic flow C --> S started");
                try
                {
                    while (true)
                    {
                        flushToServer(readFromClient(), 0);
                    }
                }
                catch (InterruptedIOException x)
                {
                    return null;
                }
                finally
                {
                    stopLatch.countDown();
                    logger.debug("Automatic flow C --> S finished");
                }
            });
            Future<Object> serverToClient = threadPool.submit(() ->
            {
                startLatch.countDown();
                logger.debug("Automatic flow C <-- S started");
                try
                {
                    while (true)
                    {
                        flushToClient(readFromServer());
                    }
                }
                catch (InterruptedIOException x)
                {
                    return null;
                }
                finally
                {
                    stopLatch.countDown();
                    logger.debug("Automatic flow C <-- S finished");
                }
            });
            Assert.assertTrue(startLatch.await(5, TimeUnit.SECONDS));
            return new SslBytesServerTest.SimpleProxy.AutomaticFlow(stopLatch, clientToServer, serverToClient);
        }
