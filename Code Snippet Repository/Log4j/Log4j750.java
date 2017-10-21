    private JeroMqAppender(final String name, final Filter filter, final Layout<? extends Serializable> layout,
            final boolean ignoreExceptions, final List<String> endpoints, final long affinity, final long backlog,
            final boolean delayAttachOnConnect, final byte[] identity, final boolean ipv4Only, final long linger,
            final long maxMsgSize, final long rcvHwm, final long receiveBufferSize, final int receiveTimeOut,
            final long reconnectIVL, final long reconnectIVLMax, final long sendBufferSize, final int sendTimeOut,
            final long sndHWM, final int tcpKeepAlive, final long tcpKeepAliveCount, final long tcpKeepAliveIdle,
            final long tcpKeepAliveInterval, final boolean xpubVerbose) {
        super(name, filter, layout, ignoreExceptions);
        this.manager = JeroMqManager.getJeroMqManager(name, affinity, backlog, delayAttachOnConnect, identity, ipv4Only,
            linger, maxMsgSize, rcvHwm, receiveBufferSize, receiveTimeOut, reconnectIVL, reconnectIVLMax,
            sendBufferSize, sendTimeOut, sndHWM, tcpKeepAlive, tcpKeepAliveCount, tcpKeepAliveIdle,
            tcpKeepAliveInterval, xpubVerbose, endpoints);
        this.endpoints = endpoints;
    }
