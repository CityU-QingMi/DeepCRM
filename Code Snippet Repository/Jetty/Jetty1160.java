    @Test
    public void testNameInsensitivity()
    {
        HpackContext ctx = new HpackContext(4096);
        assertEquals("content-length",ctx.get("content-length").getHttpField().getName());
        assertEquals("content-length",ctx.get("Content-Length").getHttpField().getName());
        assertTrue(ctx.get("Content-Length").isStatic());
        assertTrue(ctx.get("Content-Type").isStatic());
        
        ctx.add(new HttpField("Wibble","Wobble"));
        assertEquals("Wibble",ctx.get("wibble").getHttpField().getName());
        assertEquals("Wibble",ctx.get("Wibble").getHttpField().getName());
        
    }
