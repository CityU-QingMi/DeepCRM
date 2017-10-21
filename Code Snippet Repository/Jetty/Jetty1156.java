    @Test
    public void testEvictNames()
    {
        HpackContext ctx = new HpackContext(38*2);
        HttpField[] field = 
        {
           new HttpField("name","v0"),
           new HttpField("name","v1"),
           new HttpField("name","v2"),
           new HttpField("name","v3"),
           new HttpField("name","v4"),
           new HttpField("name","v5"),
        };
        
        Entry[] entry = new Entry[field.length];
        
        // Add 2 name entries to fill table
        for (int i=0;i<=1;i++)
            entry[i]=ctx.add(field[i]);
        
        // check there is a name reference and it is the most recent added
        assertEquals(entry[1],ctx.get("name"));

        // Add 1 other entry to table and evict 1
        ctx.add(new HttpField("xxx","yyy"));
        
        // check the name reference has been not been evicted
        assertEquals(entry[1],ctx.get("name"));
        
        // Add 1 other entry to table and evict 1
        ctx.add(new HttpField("foo","bar"));
        
        // name is evicted
        assertNull(ctx.get("name"));
    }
