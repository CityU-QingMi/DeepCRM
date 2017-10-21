    @Test
    public void test_FillInterested_RacingWith_BufferRelease() throws Exception
    {
        connection = new HttpConnectionOverHTTP(endPoint, destination, new Promise.Adapter<>())
        {
            @Override
            protected HttpChannelOverHTTP newHttpChannel()
            {
                return new HttpChannelOverHTTP(this)
                {
                    @Override
                    protected HttpReceiverOverHTTP newHttpReceiver()
                    {
                        return new HttpReceiverOverHTTP(this)
                        {
                            @Override
                            protected void fillInterested()
                            {
                                // Verify that the buffer has been released
                                // before fillInterested() is called.
                                Assert.assertNull(getResponseBuffer());
                                // Fill the endpoint so receive is called again.
                                endPoint.addInput("X");
                                super.fillInterested();
                            }
                        };
                    }
                };
            }
        };
        endPoint.setConnection(connection);
        
        // Partial response to trigger the call to fillInterested().
        endPoint.addInput("" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 1\r\n" +
                "\r\n");

        HttpExchange exchange = newExchange();
        FutureResponseListener listener = (FutureResponseListener)exchange.getResponseListeners().get(0);
        connection.getHttpChannel().receive();

        Response response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
