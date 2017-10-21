    public HttpChannelOverFCGI(final HttpConnectionOverFCGI connection, Flusher flusher, int request, long idleTimeout)
    {
        super(connection.getHttpDestination());
        this.connection = connection;
        this.flusher = flusher;
        this.request = request;
        this.sender = new HttpSenderOverFCGI(this);
        this.receiver = new HttpReceiverOverFCGI(this);
        this.idle = new FCGIIdleTimeout(connection, idleTimeout);
    }
