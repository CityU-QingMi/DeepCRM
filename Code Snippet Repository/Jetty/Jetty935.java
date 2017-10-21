    private void handleAuthentication(HttpServletResponse resp, HttpExchange httpExchange, Authenticator auth) throws IOException
    {
        Result result = auth.authenticate(httpExchange);
        if (result instanceof Authenticator.Failure)
        {
            int rc = ((Authenticator.Failure)result).getResponseCode();
            for (Map.Entry<String,List<String>> header : httpExchange.getResponseHeaders().entrySet())
            {
                for (String value : header.getValue())
                    resp.addHeader(header.getKey(),value);
            }
            resp.sendError(rc);
        }
        else if (result instanceof Authenticator.Retry)
        {
            int rc = ((Authenticator.Retry)result).getResponseCode();
            for (Map.Entry<String,List<String>> header : httpExchange.getResponseHeaders().entrySet())
            {
                for (String value : header.getValue())
                    resp.addHeader(header.getKey(),value);
            }
            resp.setStatus(rc);
            resp.flushBuffer();
        }
        else if (result instanceof Authenticator.Success)
        {
            HttpPrincipal principal = ((Authenticator.Success)result).getPrincipal();
            ((JettyExchange)httpExchange).setPrincipal(principal);
            _httpHandler.handle(httpExchange);
        }
    }
