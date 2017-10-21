    @Override
    public void exchangeTerminated(HttpExchange exchange, Result result)
    {
        super.exchangeTerminated(exchange, result);
        idle.onClose();
        HttpFields responseHeaders = result.getResponse().getHeaders();
        if (result.isFailed())
            connection.close(result.getFailure());
        else if (!connection.closeByHTTP(responseHeaders))
            release();
    }
