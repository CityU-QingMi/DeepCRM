    @Test
    public void testLFBlocking() throws Exception
    {
        _queue.add("");
        _queue.add("\n");
        _queue.add("Hello");
        _queue.add("\nWorld\n");
        _queue.add("\n");
        _queue.add("__CLOSE__");
        
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals("Hello",_in.readLine());
        Assert.assertEquals("World",_in.readLine());
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals(null,_in.readLine());
    }
