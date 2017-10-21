    @Override
    public void onClosed(Connection connection)
    {
        if (isStarted())
        {
            long msgsIn=connection.getMessagesIn();
            long msgsOut=connection.getMessagesOut();
            _messagesIn.set(msgsIn);
            _messagesOut.set(msgsOut);
            _connectionStats.decrement();
            _connectionDurationStats.set(System.currentTimeMillis()-connection.getCreatedTimeStamp());

            Sample sample=_samples.remove(connection);
            if (sample!=null)
            {
                _closedIn.add(msgsIn-sample._messagesIn);
                _closedOut.add(msgsOut-sample._messagesOut);
            }
        }
    }
