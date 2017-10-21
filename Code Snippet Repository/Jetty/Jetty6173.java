    @Test
    @Ignore ("")
    public void testTwoQuotes() throws Exception
    {
        QuotesSocket quoter = new QuotesSocket();
        QuoteServer qserver = new QuoteServer(server);
        new Thread(qserver).start();
        client.connectToServer(quoter,server.getWsUri());
        qserver.awaitConnect();
        qserver.writeQuotes("quotes-ben.txt");
        qserver.writeQuotes("quotes-twain.txt");
        quoter.messageQueue.awaitEventCount(2,1000,TimeUnit.MILLISECONDS);
        qserver.close();
        quoter.awaitClose();
        Quotes quotes = quoter.messageQueue.poll();
        Assert.assertThat("Quotes Author",quotes.author,is("Benjamin Franklin"));
        Assert.assertThat("Quotes Count",quotes.quotes.size(),is(3));
    }
