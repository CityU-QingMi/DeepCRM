    private JeroMqManager(final String name, final JeroMqConfiguration config) {
        super(null, name);
        publisher = CONTEXT.socket(ZMQ.PUB);
        publisher.setAffinity(config.affinity);
        publisher.setBacklog(config.backlog);
        publisher.setDelayAttachOnConnect(config.delayAttachOnConnect);
        if (config.identity != null) {
            publisher.setIdentity(config.identity);
        }
        publisher.setIPv4Only(config.ipv4Only);
        publisher.setLinger(config.linger);
        publisher.setMaxMsgSize(config.maxMsgSize);
        publisher.setRcvHWM(config.rcvHwm);
        publisher.setReceiveBufferSize(config.receiveBufferSize);
        publisher.setReceiveTimeOut(config.receiveTimeOut);
        publisher.setReconnectIVL(config.reconnectIVL);
        publisher.setReconnectIVLMax(config.reconnectIVLMax);
        publisher.setSendBufferSize(config.sendBufferSize);
        publisher.setSendTimeOut(config.sendTimeOut);
        publisher.setSndHWM(config.sndHwm);
        publisher.setTCPKeepAlive(config.tcpKeepAlive);
        publisher.setTCPKeepAliveCount(config.tcpKeepAliveCount);
        publisher.setTCPKeepAliveIdle(config.tcpKeepAliveIdle);
        publisher.setTCPKeepAliveInterval(config.tcpKeepAliveInterval);
        publisher.setXpubVerbose(config.xpubVerbose);
        for (final String endpoint : config.endpoints) {
            publisher.bind(endpoint);
        }
        LOGGER.debug("Created JeroMqManager with {}", config);
    }
