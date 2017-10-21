    public void notifyOutgoing(ByteBuffer view)
    {
        if (listeners != null && !listeners.isEmpty() && view.hasRemaining())
        {
            Socket socket=getSocket();
            for (NetworkTrafficListener listener : listeners)
            {
                try
                {
                    listener.outgoing(socket, view);   
                }
                catch (Exception x)
                {
                    LOG.warn(x);
                }
            }
        }
    }
