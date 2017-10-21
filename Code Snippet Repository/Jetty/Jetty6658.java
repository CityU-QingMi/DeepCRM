    public MessageWriter(OutgoingFrames outgoing, int bufferSize, ByteBufferPool bufferPool)
    {
        this.outgoing = outgoing;
        this.bufferPool = bufferPool;
        this.blocker = new BlockingWriteCallback();
        this.buffer = bufferPool.acquire(bufferSize, true);
        BufferUtil.flipToFill(buffer);
        this.frame = new TextFrame();
        this.utf = Utf8CharBuffer.wrap(buffer);
    }
