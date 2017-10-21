    private void flush(boolean fin) throws IOException
    {
        synchronized (this)
        {
            if (closed)
                throw new IOException("Stream is closed");

            closed = fin;

            ByteBuffer data = utf.getByteBuffer();
            if (LOG.isDebugEnabled())
                LOG.debug("flush({}): {}", fin, BufferUtil.toDetailString(buffer));
            frame.setPayload(data);
            frame.setFin(fin);

            try (WriteBlocker b = blocker.acquireWriteBlocker())
            {
                outgoing.outgoingFrame(frame, b, BatchMode.OFF);
                b.block();
            }

            ++frameCount;
            // Any flush after the first will be a CONTINUATION frame.
            frame.setIsContinuation();

            utf.clear();
        }
    }
