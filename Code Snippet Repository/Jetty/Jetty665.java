    @Test
    public void test_Receive_ResponseContent_EarlyEOF() throws Exception
    {
        String content1 = "0123456789";
        String content2 = "ABCDEF";
        endPoint.addInput("" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-length: " + (content1.length() + content2.length()) + "\r\n" +
                "\r\n" +
                content1);
        HttpExchange exchange = newExchange();
        FutureResponseListener listener = (FutureResponseListener)exchange.getResponseListeners().get(0);
        connection.getHttpChannel().receive();
        endPoint.addInputEOF();
        connection.getHttpChannel().receive();

        try
        {
            listener.get(5, TimeUnit.SECONDS);
            Assert.fail();
        }
        catch (ExecutionException e)
        {
            Assert.assertTrue(e.getCause() instanceof EOFException);
        }
    }
