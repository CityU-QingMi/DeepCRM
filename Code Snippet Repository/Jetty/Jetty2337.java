    @Override
    public final void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
    {
        int remaining = input.remaining();
        if (remaining > 0)
        {
            inputBufferSize += remaining;
            long max = getMaxInputBufferSize();
            if (max >= 0 && inputBufferSize > max)
            {
                overflow(input);
            }
            else
            {
                ByteBuffer copy = ByteBuffer.allocate(input.remaining());
                copy.put(input).flip();
                sourceBuffers.add(copy);
            }
        }

        if (finished)
        {
            Source source = new Source();
            Sink sink = new Sink();
            if (transform(source, sink))
                sink.drainTo(output);
            else
                source.drainTo(output);
        }
    }
