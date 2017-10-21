    public void window(IStream stream, WindowUpdateFrame frame)
    {
        Throwable closed;
        synchronized (this)
        {
            closed = terminated;
            if (closed == null)
                windows.offer(new WindowEntry(stream, frame));
        }
        // Flush stalled data.
        if (closed == null)
            iterate();
    }
