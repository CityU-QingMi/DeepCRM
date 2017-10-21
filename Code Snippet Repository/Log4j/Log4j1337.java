    public void apply(final Socket socket) throws SocketException {
        if (keepAlive != null) {
            socket.setKeepAlive(keepAlive.booleanValue());
        }
        if (oobInline != null) {
            socket.setOOBInline(oobInline.booleanValue());
        }
        if (reuseAddress != null) {
            socket.setReuseAddress(reuseAddress.booleanValue());
        }
        if (performancePreferences != null) {
            performancePreferences.apply(socket);
        }
        if (receiveBufferSize != null) {
            socket.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (soLinger != null) {
            socket.setSoLinger(true, soLinger.intValue());
        }
        if (soTimeout != null) {
            socket.setSoTimeout(soTimeout.intValue());
        }
        if (tcpNoDelay != null) {
            socket.setTcpNoDelay(tcpNoDelay.booleanValue());
        }
        final Integer actualTrafficClass = getActualTrafficClass();
        if (actualTrafficClass != null) {
            socket.setTrafficClass(actualTrafficClass);
        }
    }
