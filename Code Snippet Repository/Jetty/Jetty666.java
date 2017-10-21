    @Test
    public void test_Receive_ResponseContent_IdleTimeout() throws Exception
    {
        endPoint.addInput("" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-length: 1\r\n" +
                "\r\n");
        HttpExchange exchange = newExchange();
        FutureResponseListener listener = (FutureResponseListener)exchange.getResponseListeners().get(0);
        connection.getHttpChannel().receive();
        // ByteArrayEndPoint has an idle timeout of 0 by default,
        // so to simulate an idle timeout is enough to wait a bit.
        Thread.sleep(100);
        connection.onIdleExpired();

        try
        {
            listener.get(5, TimeUnit.SECONDS);
            Assert.fail();
        }
        catch (ExecutionException e)
        {
            Assert.assertTrue(e.getCause() instanceof TimeoutException);
        }
    }
