    @Test
    public void testCRBlocking() throws Exception
    {
        _queue.add("");
        _queue.add("\r");
        _queue.add("Hello");
        _queue.add("\rWorld\r");
        _queue.add("\r");
        _queue.add("__CLOSE__");
        
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals("Hello",_in.readLine());
        Assert.assertEquals("World",_in.readLine());
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals(null,_in.readLine());
    }
