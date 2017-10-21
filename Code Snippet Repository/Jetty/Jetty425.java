    public void flush() throws IOException
    {
        synchronized (lock)
        {
            try
            {
                while (true)
                {
                    if (failure != null)
                        throw new IOException(failure);
                    if (size == 0)
                        break;
                    lock.wait();
                }
            }
            catch (InterruptedException x)
            {
                throw new InterruptedIOException();
            }
        }
    }
