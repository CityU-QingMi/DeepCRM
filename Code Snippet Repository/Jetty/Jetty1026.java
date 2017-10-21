    public HTTP2Connection(ByteBufferPool byteBufferPool, ReservedThreadExecutor executor, EndPoint endPoint, Parser parser, ISession session, int bufferSize)
    {
        super(endPoint, executor.getExecutor());
        this.byteBufferPool = byteBufferPool;
        this.parser = parser;
        this.session = session;
        this.bufferSize = bufferSize;
        this.strategy = new EatWhatYouKill(producer, executor.getExecutor(), executor);
        LifeCycle.start(strategy);
    }
