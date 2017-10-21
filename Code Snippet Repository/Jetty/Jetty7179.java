    @OnWebSocketMessage
    public void onBinary(byte buf[], int offset, int len) throws IOException
    {
        LOG.debug("onBinary(byte[{}],{},{})",buf.length,offset,len);

        // echo the message back.
        ByteBuffer data = ByteBuffer.wrap(buf,offset,len);
        RemoteEndpoint remote = this.session.getRemote();
        remote.sendBytes(data, null);
        if (remote.getBatchMode() == BatchMode.ON)
            remote.flush();
    }
