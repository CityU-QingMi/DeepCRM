    public synchronized Result execute(Result r) {

        openConnection(host, port, isTLS);

        Result result = super.execute(r);

        closeConnection();

        return result;
    }
