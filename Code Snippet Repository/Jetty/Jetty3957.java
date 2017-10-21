    private boolean isLoopbackAddress(String address)
    {
        try
        {
            InetAddress addr = InetAddress.getByName(address); 
            return addr.isLoopbackAddress();
        }
        catch (UnknownHostException e )
        {
            LOG.warn("Warning: attempt to access statistics servlet from " + address, e);
            return false;
        }
    }
