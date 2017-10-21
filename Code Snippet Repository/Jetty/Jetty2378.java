    private void sendConnectResponse(HttpServletRequest request, HttpServletResponse response, int statusCode)
    {
        try
        {
            response.setStatus(statusCode);
            response.setContentLength(0);
            if (statusCode != HttpServletResponse.SC_OK)
                response.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
            response.getOutputStream().close();
            if (LOG.isDebugEnabled())
                LOG.debug("CONNECT response sent {} {}", request.getProtocol(), response.getStatus());
        }
        catch (IOException x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Could not send CONNECT response", x);
        }
    }
