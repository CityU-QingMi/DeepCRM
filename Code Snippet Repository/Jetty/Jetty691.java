    private void closeClient(SSLSocket client) throws Exception
    {
        client.close();

        // Close Alert
        TLSRecord record = proxy.readFromClient();
        proxy.flushToServer(record);
        // Socket close
        record = proxy.readFromClient();
        Assert.assertNull(String.valueOf(record), record);
        proxy.flushToServer(record);

        // Socket close
        record = proxy.readFromServer();
        if (record!=null)
        {
            Assert.assertEquals(record.getType(),Type.ALERT);
            record = proxy.readFromServer();
        }
        Assert.assertNull(record);
    }
