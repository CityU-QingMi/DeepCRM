    public void connectionClosed(ConnectionEvent event) {
        PooledConnection connection = (PooledConnection) event.getSource();

        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == connection) {
                states.set(i, RefState.available);

                break;
            }
        }
    }
