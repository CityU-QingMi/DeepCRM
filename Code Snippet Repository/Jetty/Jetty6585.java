    private void saveFrame(Frame frame, boolean outgoing)
    {
        if (outputDir == null || generator == null)
        {
            return;
        }

        @SuppressWarnings("resource")
        SeekableByteChannel channel = (outgoing) ? outgoingChannel : incomingChannel;
        
        if (channel == null)
        {
            return;
        }

        ByteBuffer buf = getBufferPool().acquire(BUFSIZE,false);

        try
        {
            WebSocketFrame f = WebSocketFrame.copy(frame);
            f.setMasked(false);
            generator.generateHeaderBytes(f,buf);
            channel.write(buf);
            if (frame.hasPayload())
            {
                channel.write(frame.getPayload().slice());
            }
            if (LOG.isDebugEnabled())
                LOG.debug("Saved {} frame #{}",(outgoing) ? "outgoing" : "incoming",
                        (outgoing) ? outgoingCount.incrementAndGet() : incomingCount.incrementAndGet());
        }
        catch (IOException e)
        {
            LOG.warn("Unable to save frame: " + frame,e);
        }
        finally
        {
            getBufferPool().release(buf);
        }
    }
