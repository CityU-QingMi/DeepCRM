    public void disconnect()
    {
        LOG.debug("disconnect");
        IO.close(in);
        IO.close(out);
        disconnectedLatch.countDown();
        if (frameReader != null)
        {
            frameReader.interrupt();
        }
        if (socket != null)
        {
            try
            {
                socket.close();
            }
            catch (IOException ignore)
            {
                /* ignore */
            }
        }
    }
