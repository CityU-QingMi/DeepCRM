    @Test
    public void testGetAddStaticName()
    {
        HpackContext ctx = new HpackContext(4096);
        HttpField methodOther = new HttpField(":method","OTHER");

        // Look for the field by name.  Should find static version.
        assertEquals(":method",ctx.get(":method").getHttpField().getName());
        assertTrue(ctx.get(":method").isStatic());
        
        // Add dynamic entry with method
        ctx.add(methodOther);
        
        // Look for the field by name.  Should find static version.
        assertEquals(":method",ctx.get(":method").getHttpField().getName());
        assertTrue(ctx.get(":method").isStatic()); 
    }
