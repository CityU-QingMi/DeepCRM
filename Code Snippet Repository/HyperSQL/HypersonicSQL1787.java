    synchronized public void connectionErrorOccurred(SQLException e) {

        ConnectionEvent event = new ConnectionEvent(this, e);

        reset();

        for (int i = 0; i < listeners.size(); i++) {
            ConnectionEventListener connectionEventListener =
                (ConnectionEventListener) listeners.get(i);

            connectionEventListener.connectionErrorOccurred(event);
        }
    }
