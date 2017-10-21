    public void disconnect()
    {
        LOG.debug("disconnect");
        IO.close(in);
        IO.close(out);
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
