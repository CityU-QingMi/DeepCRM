    protected HttpExchange newExchange()
    {
        HttpRequest request = (HttpRequest)client.newRequest("http://localhost");
        FutureResponseListener listener = new FutureResponseListener(request);
        HttpExchange exchange = new HttpExchange(destination, request, Collections.<Response.ResponseListener>singletonList(listener));
        boolean associated = connection.getHttpChannel().associate(exchange);
        Assert.assertTrue(associated);
        exchange.requestComplete(null);
        exchange.terminateRequest();
        return exchange;
    }
