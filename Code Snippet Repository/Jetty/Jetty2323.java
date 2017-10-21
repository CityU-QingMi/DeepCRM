    protected void onClientRequestFailure(HttpServletRequest clientRequest, Request proxyRequest, HttpServletResponse proxyResponse, Throwable failure)
    {
        boolean aborted = proxyRequest.abort(failure);
        if (!aborted)
        {
            int status = failure instanceof TimeoutException ?
                    HttpStatus.REQUEST_TIMEOUT_408 :
                    HttpStatus.INTERNAL_SERVER_ERROR_500;
            sendProxyResponseError(clientRequest, proxyResponse, status);
        }
    }
