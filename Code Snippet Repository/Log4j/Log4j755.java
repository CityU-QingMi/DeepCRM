        @Override
        public String toString() {
            return "JeroMqConfiguration{" +
                "affinity=" + affinity +
                ", backlog=" + backlog +
                ", delayAttachOnConnect=" + delayAttachOnConnect +
                ", identity=" + Arrays.toString(identity) +
                ", ipv4Only=" + ipv4Only +
                ", linger=" + linger +
                ", maxMsgSize=" + maxMsgSize +
                ", rcvHwm=" + rcvHwm +
                ", receiveBufferSize=" + receiveBufferSize +
                ", receiveTimeOut=" + receiveTimeOut +
                ", reconnectIVL=" + reconnectIVL +
                ", reconnectIVLMax=" + reconnectIVLMax +
                ", sendBufferSize=" + sendBufferSize +
                ", sendTimeOut=" + sendTimeOut +
                ", sndHwm=" + sndHwm +
                ", tcpKeepAlive=" + tcpKeepAlive +
                ", tcpKeepAliveCount=" + tcpKeepAliveCount +
                ", tcpKeepAliveIdle=" + tcpKeepAliveIdle +
                ", tcpKeepAliveInterval=" + tcpKeepAliveInterval +
                ", xpubVerbose=" + xpubVerbose +
                ", endpoints=" + endpoints +
                '}';
        }
