    @Override
    public void write(int c) throws IOException
    {
        try
        {
            send(new char[]{(char)c}, 0, 1);
        }
        catch (Throwable x)
        {
            // Notify without holding locks.
            notifyFailure(x);
            throw x;
        }
    }
