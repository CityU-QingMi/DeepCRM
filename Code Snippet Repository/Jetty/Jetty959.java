    @Override
    public Connection newConnection(EndPoint endPoint, Map<String, Object> context) throws IOException
    {
        HTTP2Client client = (HTTP2Client)context.get(CLIENT_CONTEXT_KEY);
        ByteBufferPool byteBufferPool = (ByteBufferPool)context.get(BYTE_BUFFER_POOL_CONTEXT_KEY);
        Executor executor = (Executor)context.get(EXECUTOR_CONTEXT_KEY);
        Scheduler scheduler = (Scheduler)context.get(SCHEDULER_CONTEXT_KEY);
        Session.Listener listener = (Session.Listener)context.get(SESSION_LISTENER_CONTEXT_KEY);
        @SuppressWarnings("unchecked")
        Promise<Session> promise = (Promise<Session>)context.get(SESSION_PROMISE_CONTEXT_KEY);

        Generator generator = new Generator(byteBufferPool);
        FlowControlStrategy flowControl = client.getFlowControlStrategyFactory().newFlowControlStrategy();
        HTTP2ClientSession session = new HTTP2ClientSession(scheduler, endPoint, generator, listener, flowControl);
        Parser parser = new Parser(byteBufferPool, session, 4096, 8192);

        ReservedThreadExecutor reservedExecutor = provideReservedThreadExecutor(client, executor);

        HTTP2ClientConnection connection = new HTTP2ClientConnection(client, byteBufferPool, reservedExecutor, endPoint,
                parser, session, client.getInputBufferSize(), promise, listener);
        connection.addListener(connectionListener);
        return customize(connection, context);
    }
