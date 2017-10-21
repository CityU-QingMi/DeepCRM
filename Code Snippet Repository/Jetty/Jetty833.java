    @Override
    public void send()
    {
        HttpExchange exchange = getHttpExchange();
        if (exchange != null)
        {
            version = exchange.getRequest().getVersion();
            idle.onOpen();
            sender.send(exchange);
        }
    }
