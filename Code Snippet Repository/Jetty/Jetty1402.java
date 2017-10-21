    @Test
    public void testValues()
    {
        String[] values = new HttpField("name","value").getValues();
        assertEquals(1,values.length);
        assertEquals("value",values[0]);
        

        values = new HttpField("name","a,b,c").getValues();
        assertEquals(3,values.length);
        assertEquals("a",values[0]);
        assertEquals("b",values[1]);
        assertEquals("c",values[2]);

        values = new HttpField("name","a,\"x,y,z\",c").getValues();
        assertEquals(3,values.length);
        assertEquals("a",values[0]);
        assertEquals("x,y,z",values[1]);
        assertEquals("c",values[2]);
        
        values = new HttpField("name","a,\"x,\\\"p,q\\\",z\",c").getValues();
        assertEquals(3,values.length);
        assertEquals("a",values[0]);
        assertEquals("x,\"p,q\",z",values[1]);
        assertEquals("c",values[2]);
        
    }
