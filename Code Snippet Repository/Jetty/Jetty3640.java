    @Test
    public void testWriteRuntimeIOException() throws Exception
    {
        Response response = getResponse();

        PrintWriter writer = response.getWriter();
        writer.println("test");
        writer.flush();
        Assert.assertFalse(writer.checkError());

        Throwable cause = new IOException("problem at mill");
        _channel.abort(cause);
        writer.println("test");
        Assert.assertTrue(writer.checkError());
        try
        {
            writer.println("test");
            Assert.fail();
        }
        catch(RuntimeIOException e)
        {
            Assert.assertEquals(cause,e.getCause());
        }

    }
