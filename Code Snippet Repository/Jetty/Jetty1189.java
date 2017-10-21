    @Override
    protected SendFailure send(HttpExchange exchange)
    {
        exchange.getRequest().version(HttpVersion.HTTP_2);
        normalizeRequest(exchange.getRequest());

        // One connection maps to N channels, so for each exchange we create a new channel.
        HttpChannel channel = newHttpChannel(false);
        channels.add(channel);

        return send(channel, exchange);
    }
