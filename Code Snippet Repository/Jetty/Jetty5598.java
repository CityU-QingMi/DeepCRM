    @Test
    public void testCRLFBlocking() throws Exception
    {
        _queue.add("\r");
        _queue.add("\nHello");
        _queue.add("\r\nWorld\r");
        _queue.add("\n\r");
        _queue.add("\n");
        _queue.add("");
        _queue.add("__CLOSE__");
        
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals("Hello",_in.readLine());
        Assert.assertEquals("World",_in.readLine());
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals(null,_in.readLine());
    }
