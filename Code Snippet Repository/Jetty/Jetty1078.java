    @Override
    public void onDataConsumed(ISession session, IStream stream, int length)
    {
        if (length <= 0)
            return;

        // This is the simple algorithm for flow control.
        // This method is called when a whole flow controlled frame has been consumed.
        // We send a WindowUpdate every time, even if the frame was very small.

        WindowUpdateFrame sessionFrame = new WindowUpdateFrame(0, length);
        session.updateRecvWindow(length);
        if (LOG.isDebugEnabled())
            LOG.debug("Data consumed, increased session recv window by {} for {}", length, session);

        Frame[] streamFrame = Frame.EMPTY_ARRAY;
        if (stream != null)
        {
            if (stream.isRemotelyClosed())
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Data consumed, ignoring update stream recv window by {} for remotely closed {}", length, stream);
            }
            else
            {
                streamFrame = new Frame[1];
                streamFrame[0] = new WindowUpdateFrame(stream.getId(), length);
                stream.updateRecvWindow(length);
                if (LOG.isDebugEnabled())
                    LOG.debug("Data consumed, increased stream recv window by {} for {}", length, stream);
            }
        }

        session.frames(stream, Callback.NOOP, sessionFrame, streamFrame);
    }
