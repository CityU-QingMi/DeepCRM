    public Result cancel(Result result) {

        ClientConnection connection = new ClientConnection(this);

        try {
            return connection.execute(result);
        } finally {
            connection.closeConnection();
        }
    }
