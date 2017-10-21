    public HttpConnection(HttpConfiguration config, Connector connector, EndPoint endPoint, HttpCompliance compliance, boolean recordComplianceViolations)
    {
        super(endPoint, connector.getExecutor());
        _config = config;
        _connector = connector;
        _bufferPool = _connector.getByteBufferPool();
        _generator = newHttpGenerator();
        _channel = newHttpChannel();
        _input = _channel.getRequest().getHttpInput();
        _parser = newHttpParser(compliance);
        _recordHttpComplianceViolations=recordComplianceViolations;
        if (LOG.isDebugEnabled())
            LOG.debug("New HTTP Connection {}", this);
    }
