    @Override
    public boolean messageComplete()
    {
        HttpExchange exchange = getHttpExchange();
        if (exchange == null)
            return false;

        boolean proceed = responseSuccess(exchange);
        if (!proceed)
            return true;

        int status = exchange.getResponse().getStatus();
        if (status == HttpStatus.SWITCHING_PROTOCOLS_101)
            return true;

        if (HttpMethod.CONNECT.is(exchange.getRequest().getMethod()) &&
                status == HttpStatus.OK_200)
            return true;

        return false;
    }
