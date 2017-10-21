    @Test
    public void testGetAddStatic()
    {
        HpackContext ctx = new HpackContext(4096);

        // Look for the field.  Should find static version.
        HttpField methodGet = new HttpField(":method","GET");
        assertEquals(methodGet,ctx.get(methodGet).getHttpField());
        assertTrue(ctx.get(methodGet).isStatic());
        
        // Add static version to dynamic table
        Entry e0=ctx.add(ctx.get(methodGet).getHttpField());
        
        // Look again and should see dynamic version
        assertEquals(methodGet,ctx.get(methodGet).getHttpField());
        assertFalse(methodGet==ctx.get(methodGet).getHttpField());
        assertFalse(ctx.get(methodGet).isStatic());
        
        // Duplicates allows
        Entry e1=ctx.add(ctx.get(methodGet).getHttpField());
        
        // Look again and should see dynamic version
        assertEquals(methodGet,ctx.get(methodGet).getHttpField());
        assertFalse(methodGet==ctx.get(methodGet).getHttpField());
        assertFalse(ctx.get(methodGet).isStatic());
        assertFalse(e0==e1);
    }
