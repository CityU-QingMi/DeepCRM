    @Test
    public void testCRLF() throws Exception
    {
        _queue.add("\r\nHello\r\nWorld\r\n\r\n");
        _queue.add("__CLOSE__");
        
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals("Hello",_in.readLine());
        Assert.assertEquals("World",_in.readLine());
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals(null,_in.readLine());
    }
