    @Override
    public void onData(Stream stream, DataFrame frame, Callback callback)
    {
        HttpExchange exchange = getHttpExchange();
        if (exchange == null)
        {
            callback.failed(new IOException("terminated"));
            return;
        }

        // We must copy the data since we do not know when the
        // application will consume the bytes and the parsing
        // will continue as soon as this method returns, eventually
        // leading to reusing the underlying buffer for more reads.
        ByteBufferPool byteBufferPool = getHttpDestination().getHttpClient().getByteBufferPool();
        ByteBuffer original = frame.getData();
        int length = original.remaining();
        final ByteBuffer copy = byteBufferPool.acquire(length, original.isDirect());
        BufferUtil.clearToFill(copy);
        copy.put(original);
        BufferUtil.flipToFlush(copy, 0);

        contentNotifier.offer(new DataInfo(exchange, copy, callback, frame.isEndStream()));
        contentNotifier.iterate();
    }
