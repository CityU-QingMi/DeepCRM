    public HttpConnectionOverFCGI(EndPoint endPoint, HttpDestination destination, Promise<Connection> promise, boolean multiplexed)
    {
        super(endPoint, destination.getHttpClient().getExecutor());
        this.destination = destination;
        this.promise = promise;
        this.multiplexed = multiplexed;
        this.flusher = new Flusher(endPoint);
        this.delegate = new Delegate(destination);
        this.parser = new ClientParser(new ResponseListener());
        requests.addLast(0);
    }
