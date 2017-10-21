    @Test
    public void testStripComment()
    {
        String test="\n\n\n\t\t    "+
        "// ignore this ,a [ \" \n"+
        "/* "+
        "{ "+
        "\"onehundred\" : 100  ,"+
        "\"name\" : \"fred\"  ," +
        "\"empty\" : {}  ," +
        "\"map\" : {\"a\":-1.0e2}  ," +
        "\"array\" : [\"a\",-1.0e2,[],null,true,false]  ," +
        "} */";

        Object o = JSON.parse(test,false);
        assertTrue(o==null);
        o = JSON.parse(test,true);
        assertTrue(o instanceof Map);
        assertEquals("fred",((Map)o).get("name"));

    }
