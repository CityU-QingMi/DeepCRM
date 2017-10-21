    public void stop()
    {
        if (server != null)
        {
            try
            {
                server.stop();
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
            }
        }
    }
