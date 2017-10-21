    @AfterClass
    public static void stopEnv()
    {
        // Disconnect client
        try
        {
            session.close();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }

        // Stop server
        try
        {
            server.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
