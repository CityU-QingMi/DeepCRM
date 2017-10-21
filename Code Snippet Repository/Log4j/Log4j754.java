        private JeroMqConfiguration(final long affinity, final long backlog, final boolean delayAttachOnConnect,
                                    final byte[] identity, final boolean ipv4Only, final long linger,
                                    final long maxMsgSize, final long rcvHwm, final long receiveBufferSize,
                                    final int receiveTimeOut, final long reconnectIVL, final long reconnectIVLMax,
                                    final long sendBufferSize, final int sendTimeOut, final long sndHwm,
                                    final int tcpKeepAlive, final long tcpKeepAliveCount, final long tcpKeepAliveIdle,
                                    final long tcpKeepAliveInterval, final boolean xpubVerbose,
                                    final List<String> endpoints) {
            this.affinity = affinity;
            this.backlog = backlog;
            this.delayAttachOnConnect = delayAttachOnConnect;
            this.identity = identity;
            this.ipv4Only = ipv4Only;
            this.linger = linger;
            this.maxMsgSize = maxMsgSize;
            this.rcvHwm = rcvHwm;
            this.receiveBufferSize = receiveBufferSize;
            this.receiveTimeOut = receiveTimeOut;
            this.reconnectIVL = reconnectIVL;
            this.reconnectIVLMax = reconnectIVLMax;
            this.sendBufferSize = sendBufferSize;
            this.sendTimeOut = sendTimeOut;
            this.sndHwm = sndHwm;
            this.tcpKeepAlive = tcpKeepAlive;
            this.tcpKeepAliveCount = tcpKeepAliveCount;
            this.tcpKeepAliveIdle = tcpKeepAliveIdle;
            this.tcpKeepAliveInterval = tcpKeepAliveInterval;
            this.xpubVerbose = xpubVerbose;
            this.endpoints = endpoints;
        }
