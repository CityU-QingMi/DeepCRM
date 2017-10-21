    @Override
    public Connection newConnection(Connector connector, EndPoint endPoint)
    {
        List<String> negotiated = this.negotiatedProtocols;
        if (negotiated.isEmpty())
        {
            // Generate list of protocols that we can negotiate
            negotiated = connector.getProtocols().stream()
            .filter(p->
            {
                ConnectionFactory f=connector.getConnectionFactory(p);
                return !(f instanceof SslConnectionFactory)&&!(f instanceof NegotiatingServerConnectionFactory);
            })
            .collect(Collectors.toList());            
        }

        // if default protocol is not set, then it is either HTTP/1.1 or 
        // the first protocol given
        String dft = defaultProtocol;
        if (dft == null && !negotiated.isEmpty())
        {
            if (negotiated.contains(HttpVersion.HTTP_1_1.asString()))
                dft = HttpVersion.HTTP_1_1.asString();
            else
                dft = negotiated.get(0);
        }

        SSLEngine engine = null;
        EndPoint ep = endPoint;
        while (engine == null && ep != null)
        {
            // TODO make more generic
            if (ep instanceof SslConnection.DecryptedEndPoint)
                engine = ((SslConnection.DecryptedEndPoint)ep).getSslConnection().getSSLEngine();
            else
                ep = null;
        }

        return configure(newServerConnection(connector, endPoint, engine, negotiated, dft), connector, endPoint);
    }
