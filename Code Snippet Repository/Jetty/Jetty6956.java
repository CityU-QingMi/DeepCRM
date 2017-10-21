    @Override
    public void doHandshakeResponse(ServletUpgradeRequest request, ServletUpgradeResponse response) throws IOException
    {
        String key = request.getHeader("Sec-WebSocket-Key");

        if (key == null)
        {
            throw new IllegalStateException("Missing request header 'Sec-WebSocket-Key'");
        }

        // build response
        response.setHeader("Upgrade","WebSocket");
        response.addHeader("Connection","Upgrade");
        response.addHeader("Sec-WebSocket-Accept",AcceptHash.hashKey(key));

        request.complete();

        response.setStatusCode(HttpServletResponse.SC_SWITCHING_PROTOCOLS);
        response.complete();
    }
