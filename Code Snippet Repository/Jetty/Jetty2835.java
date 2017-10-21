    public HttpConfiguration(HttpConfiguration config)
    {
        _customizers.addAll(config._customizers);
        for (String s:config._formEncodedMethods.keySet())
            _formEncodedMethods.put(s,Boolean.TRUE);
        _outputBufferSize=config._outputBufferSize;
        _outputAggregationSize=config._outputAggregationSize;
        _requestHeaderSize=config._requestHeaderSize;
        _responseHeaderSize=config._responseHeaderSize;
        _headerCacheSize=config._headerCacheSize;
        _secureScheme=config._secureScheme;
        _securePort=config._securePort;
        _idleTimeout=config._idleTimeout;
        _blockingTimeout=config._blockingTimeout;
        _sendDateHeader=config._sendDateHeader;
        _sendServerVersion=config._sendServerVersion;
        _sendXPoweredBy=config._sendXPoweredBy;
        _delayDispatchUntilContent=config._delayDispatchUntilContent;
        _persistentConnectionsEnabled=config._persistentConnectionsEnabled;
        _maxErrorDispatches=config._maxErrorDispatches;
        _minRequestDataRate=config._minRequestDataRate;
        _cookieCompliance=config._cookieCompliance;
    }
