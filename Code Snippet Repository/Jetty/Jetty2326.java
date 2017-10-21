    protected void sendProxyResponseError(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, int status)
    {
        try
        {
            if (!proxyResponse.isCommitted())
            {
                proxyResponse.resetBuffer();
                proxyResponse.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
            }
            proxyResponse.sendError(status);
        }
        catch(Exception e)
        {
            _log.ignore(e);
        }
        finally
        {
            if (clientRequest.isAsyncStarted())
                clientRequest.getAsyncContext().complete();
        }
    }
