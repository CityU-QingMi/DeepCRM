    @Test
    public void testHeaderLFBodyLF() throws Exception
    {
        _queue.add("Header\n");
        _queue.add("\n");
        _queue.add("\nBody\n");
        _queue.add("\n");
        _queue.add("__CLOSE__");

        Assert.assertEquals("Header",_in.readLine());
        Assert.assertEquals("",_in.readLine());

        byte[] body = new byte[6];
        _in.read(body);
        Assert.assertEquals("\nBody\n",new String(body,0,6,StandardCharsets.UTF_8));
        
        Assert.assertEquals("",_in.readLine());
        Assert.assertEquals(null,_in.readLine());
    }
