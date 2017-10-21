    protected void onProxyResponseFailure(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Response serverResponse, Throwable failure)
    {
        if (_log.isDebugEnabled())
            _log.debug(getRequestId(clientRequest) + " proxying failed", failure);

        int status = failure instanceof TimeoutException ?
            HttpStatus.GATEWAY_TIMEOUT_504 :
                HttpStatus.BAD_GATEWAY_502;
        int serverStatus = serverResponse == null ? status : serverResponse.getStatus();
        if (expects100Continue(clientRequest) && serverStatus >= HttpStatus.OK_200)
            status = serverStatus;
        sendProxyResponseError(clientRequest, proxyResponse, status);
        
    }
