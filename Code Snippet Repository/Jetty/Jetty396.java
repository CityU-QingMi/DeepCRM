    @Override
    public Result exchangeTerminating(HttpExchange exchange, Result result)
    {
        if (result.isFailed())
            return result;

        HttpResponse response = exchange.getResponse();
        
        if ((response.getVersion() == HttpVersion.HTTP_1_1) && 
            (response.getStatus() == HttpStatus.SWITCHING_PROTOCOLS_101))
        {
            String connection = response.getHeaders().get(HttpHeader.CONNECTION);
            if ((connection == null) || !connection.toLowerCase(Locale.US).contains("upgrade"))
            {
                return new Result(result,new HttpResponseException("101 Switching Protocols without Connection: Upgrade not supported",response));
            }
            
            // Upgrade Response
            HttpRequest request = exchange.getRequest();
            if (request instanceof HttpConnectionUpgrader)
            {
                HttpConnectionUpgrader listener = (HttpConnectionUpgrader)request;
                try
                {
                    listener.upgrade(response,getHttpConnection());
                }
                catch (Throwable x)
                {
                    return new Result(result,x);
                }
            }
        }

        return result;
    }
