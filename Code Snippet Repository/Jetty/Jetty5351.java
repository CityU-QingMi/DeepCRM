    @Test
    public void testLong() throws Exception
    {
        byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
        long value=((0xffL&bytes[0])<<56)+((0xffL&bytes[1])<<48)+((0xffL&bytes[2])<<40)+((0xffL&bytes[3])<<32)+
                ((0xffL&bytes[4])<<24)+((0xffL&bytes[5])<<16)+((0xffL&bytes[6])<<8)+(0xffL&bytes[7]);
        
        StringBuilder b = new StringBuilder();
        B64Code.encode(value,b);
        Assert.assertEquals("TWFuIGlzIGQ",b.toString());
    }
