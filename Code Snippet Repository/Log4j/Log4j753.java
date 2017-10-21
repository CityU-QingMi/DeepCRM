    public static JeroMqManager getJeroMqManager(final String name, final long affinity, final long backlog,
                                                 final boolean delayAttachOnConnect, final byte[] identity,
                                                 final boolean ipv4Only, final long linger, final long maxMsgSize,
                                                 final long rcvHwm, final long receiveBufferSize,
                                                 final int receiveTimeOut, final long reconnectIVL,
                                                 final long reconnectIVLMax, final long sendBufferSize,
                                                 final int sendTimeOut, final long sndHwm, final int tcpKeepAlive,
                                                 final long tcpKeepAliveCount, final long tcpKeepAliveIdle,
                                                 final long tcpKeepAliveInterval, final boolean xpubVerbose,
                                                 final List<String> endpoints) {
        return getManager(name, FACTORY,
            new JeroMqConfiguration(affinity, backlog, delayAttachOnConnect, identity, ipv4Only, linger, maxMsgSize,
                rcvHwm, receiveBufferSize, receiveTimeOut, reconnectIVL, reconnectIVLMax, sendBufferSize, sendTimeOut,
                sndHwm, tcpKeepAlive, tcpKeepAliveCount, tcpKeepAliveIdle, tcpKeepAliveInterval, xpubVerbose,
                endpoints));
    }
