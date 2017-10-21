    public static void main(String... args)
    {
        int freeport = TestOSGiUtil.findFreePort("test");
        System.err.println("Found Free port="+freeport);

        
        try (ServerSocket socket = new ServerSocket(TestOSGiUtil.findFreePort("test")))
        {
            System.err.println("reused port="+socket.getLocalPort());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
