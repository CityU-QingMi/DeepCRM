    public void connect() throws IOException
    {
        if (!client.isConnected())
        {
            client.connect();
            client.addHeader("X-TestCase: " + testname + "\r\n");
            client.sendStandardRequest();
            client.expectUpgradeResponse();
        }
    }
