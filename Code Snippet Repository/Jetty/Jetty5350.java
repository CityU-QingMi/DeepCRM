    @Test
    public void testInteger() throws Exception
    {
        byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
        int value=(bytes[0]<<24)+(bytes[1]<<16)+(bytes[2]<<8)+(bytes[3]);
        
        StringBuilder b = new StringBuilder();
        B64Code.encode(value,b);
        Assert.assertEquals("TWFuIA=",b.toString());
    }
