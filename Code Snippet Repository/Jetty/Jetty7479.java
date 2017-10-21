    public void close(Socket sock)
    {
        if (sock != null)
        {
            try
            {
                sock.close();
            }
            catch (IOException e)
            {
                System.err.println("Unable to close socket: " + sock);
                e.printStackTrace(System.err);
            }
        }
    }
