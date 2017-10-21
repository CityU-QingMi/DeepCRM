    @Override
    public void customize(Connector connector, HttpConfiguration channelConfig, Request request)
    {
        EndPoint endp = request.getHttpChannel().getEndPoint();
        if (endp instanceof DecryptedEndPoint)
        {
            SslConnection.DecryptedEndPoint ssl_endp = (DecryptedEndPoint)endp;
            SslConnection sslConnection = ssl_endp.getSslConnection();
            SSLEngine sslEngine=sslConnection.getSSLEngine();
            customize(sslEngine,request);

            if (request.getHttpURI().getScheme()==null)
                request.setScheme(HttpScheme.HTTPS.asString());
        }
        else if (endp instanceof ProxyConnectionFactory.ProxyEndPoint)
        {
            ProxyConnectionFactory.ProxyEndPoint proxy = (ProxyConnectionFactory.ProxyEndPoint)endp;
            if (request.getHttpURI().getScheme()==null && proxy.getAttribute(ProxyConnectionFactory.TLS_VERSION)!=null)
                request.setScheme(HttpScheme.HTTPS.asString());       
        }

        if (HttpScheme.HTTPS.is(request.getScheme()))
            customizeSecure(request);
    }
