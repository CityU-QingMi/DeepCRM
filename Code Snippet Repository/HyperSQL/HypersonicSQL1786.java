    synchronized public void connectionClosed() {

        ConnectionEvent event = new ConnectionEvent(this);

        userConnection = null;

        reset();

        for (int i = 0; i < listeners.size(); i++) {
            ConnectionEventListener connectionEventListener =
                (ConnectionEventListener) listeners.get(i);

            connectionEventListener.connectionClosed(event);
        }
    }
