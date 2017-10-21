    @Test
    public void testTwoQuotes() throws Exception
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
            Quotes twain = getQuotes("quotes-twain.txt");
            quoter.write(ben);
            quoter.write(twain);

            quoter.messageQueue.awaitEventCount(2,1000,TimeUnit.MILLISECONDS);

            String result = quoter.messageQueue.poll();
            assertReceivedQuotes(result,ben);
            result = quoter.messageQueue.poll();
            assertReceivedQuotes(result,twain);
        }
        finally
        {
            eserver.stop();
        }
    }
