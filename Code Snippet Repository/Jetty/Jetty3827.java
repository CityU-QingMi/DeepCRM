    @Override
    public void testFullMethod() throws Exception
    {
        // Don't run on Windows (buggy JVM)
        Assume.assumeTrue(!OS.IS_WINDOWS);
        
        try
        {
            super.testFullMethod();
        }
        catch (SocketException e)
        {
            Log.getLogger(SslConnection.class).warn("Close overtook 400 response");
        }
    }
