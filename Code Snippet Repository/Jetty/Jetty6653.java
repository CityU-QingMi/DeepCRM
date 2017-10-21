    @Override
    public void appendFrame(ByteBuffer framePayload, boolean fin) throws IOException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Appending {} chunk: {}",fin?"final":"non-final",BufferUtil.toDetailString(framePayload));
        }

        // If closed, we should just toss incoming payloads into the bit bucket.
        if (closed.get())
        {
            return;
        }

        // Put the payload into the queue, by copying it.
        // Copying is necessary because the payload will
        // be processed after this method returns.
        try
        {
            if (framePayload == null)
            {
                // skip if no payload
                return;
            }

            int capacity = framePayload.remaining();
            if (capacity <= 0)
            {
                // skip if no payload data to copy
                return;
            }
            // TODO: the copy buffer should be pooled too, but no buffer pool available from here.
            ByteBuffer copy = framePayload.isDirect()?ByteBuffer.allocateDirect(capacity):ByteBuffer.allocate(capacity);
            copy.put(framePayload).flip();
            buffers.put(copy);
        }
        catch (InterruptedException e)
        {
            throw new IOException(e);
        }
        finally
        {
            if (fin)
            {
                buffers.offer(EOF);
            }
        }
    }
