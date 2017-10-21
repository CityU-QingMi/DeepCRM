    public void parse(ByteBuffer buffer) throws WebSocketException
    {
        if (buffer.remaining() <= 0)
        {
            return;
        }
        try
        {
            // parse through all the frames in the buffer
            while (parseFrame(buffer))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("{} Parsed Frame: {}",policy.getBehavior(),frame);
                notifyFrame(frame);
                if (frame.isDataFrame())
                {
                    priorDataFrame = !frame.isFin();
                }
                reset();
            }
        }
        catch (WebSocketException e)
        {
            buffer.position(buffer.limit()); // consume remaining
            reset();
            // let session know
            notifyWebSocketException(e);
            // need to throw for proper close behavior in connection
            throw e;
        }
        catch (Throwable t)
        {
            buffer.position(buffer.limit()); // consume remaining
            reset();
            // let session know
            WebSocketException e = new WebSocketException(t);
            notifyWebSocketException(e);
            // need to throw for proper close behavior in connection
            throw e;
        }
    }
