    @Test
    public void testEvictOne()
    {
        HpackContext ctx = new HpackContext(38);
        HttpField field0 = new HttpField("foo","bar");
        
        assertEquals(field0,ctx.add(field0).getHttpField());
        assertEquals(field0,ctx.get("foo").getHttpField());
        
        HttpField field1 = new HttpField("xxx","yyy");
        assertEquals(field1,ctx.add(field1).getHttpField());

        assertNull(ctx.get(field0));
        assertNull(ctx.get("foo"));
        assertEquals(field1,ctx.get(field1).getHttpField());
        assertEquals(field1,ctx.get("xxx").getHttpField());
        
    }
