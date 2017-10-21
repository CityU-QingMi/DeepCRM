    @Test
    public void testSingleQuotes() throws Exception
    {
        EchoServer eserver = new EchoServer(server);
        try
        {
            eserver.start();

            QuotesSocket quoter = new QuotesSocket();

            ClientEndpointConfig.Builder builder = ClientEndpointConfig.Builder.create();
            List<Class<? extends Encoder>> encoders = new ArrayList<>();
            encoders.add(QuotesEncoder.class);
            builder.encoders(encoders);
            ClientEndpointConfig cec = builder.build();
            client.connectToServer(quoter,cec,server.getWsUri());

            Quotes ben = getQuotes("quotes-ben.txt");
            quoter.write(ben);

            quoter.messageQueue.awaitEventCount(1,1000,TimeUnit.MILLISECONDS);

            String result = quoter.messageQueue.poll();
            assertReceivedQuotes(result,ben);
        }
        finally
        {
            eserver.stop();
        }
    }
