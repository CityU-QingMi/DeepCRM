    @Override
    public void onClosed(Connection connection)
    {
        if (!isStarted())
            return;

        _connections.decrement();

        long elapsed = System.currentTimeMillis() - connection.getCreatedTimeStamp();
        _connectionsDuration.set(elapsed);

        long bytesIn = connection.getBytesIn();
        if (bytesIn > 0)
            _rcvdBytes.add(bytesIn);
        long bytesOut = connection.getBytesOut();
        if (bytesOut > 0)
            _sentBytes.add(bytesOut);

        long messagesIn = connection.getMessagesIn();
        if (messagesIn > 0)
            _messagesIn.add(messagesIn);
        long messagesOut = connection.getMessagesOut();
        if (messagesOut > 0)
            _messagesOut.add(messagesOut);
    }
