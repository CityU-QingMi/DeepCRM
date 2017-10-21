    private void flush(boolean fin) throws IOException
    {
        synchronized (this)
        {
            if (closed)
                throw new IOException("Stream is closed");

            closed = fin;

            BufferUtil.flipToFlush(buffer, 0);
            frame.setPayload(buffer);
            frame.setFin(fin);

            try(WriteBlocker b=blocker.acquireWriteBlocker())
            {
                outgoing.outgoingFrame(frame, b, BatchMode.OFF);
                b.block();
            }

            ++frameCount;
            // Any flush after the first will be a CONTINUATION frame.
            frame.setIsContinuation();

            BufferUtil.flipToFill(buffer);
        }
    }
