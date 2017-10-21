    @Test
    public void test_Receive_BadResponse() throws Exception
    {
        endPoint.addInput("" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-length: A\r\n" +
                "\r\n");
        HttpExchange exchange = newExchange();
        FutureResponseListener listener = (FutureResponseListener)exchange.getResponseListeners().get(0);
        connection.getHttpChannel().receive();

        try
        {
            listener.get(5, TimeUnit.SECONDS);
            Assert.fail();
        }
        catch (ExecutionException e)
        {
            Assert.assertTrue(e.getCause() instanceof HttpResponseException);
        }
    }
