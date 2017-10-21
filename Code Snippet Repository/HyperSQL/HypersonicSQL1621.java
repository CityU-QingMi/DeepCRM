    public void connectionErrorOccurred(ConnectionEvent event) {
        PooledConnection connection = (PooledConnection) event.getSource();

        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == connection) {
                states.set(i, RefState.allocated);
                connections[i] = null;
                states.set(i, RefState.empty);
                break;
            }
        }
    }
