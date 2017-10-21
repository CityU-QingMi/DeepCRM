    @Override
    public void incomingFrame(Frame frame)
    {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        try
        {
            Thread.currentThread().setContextClassLoader(classLoader);
            if (connection.getIOState().isInputAvailable())
            {
                // Forward Frames Through Extension List
                incomingHandler.incomingFrame(frame);
            }
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(old);
        }
    }
