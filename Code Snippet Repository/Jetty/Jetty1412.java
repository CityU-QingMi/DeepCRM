    @Test
    public void testContains() throws Exception
    {
        HttpFields header = new HttpFields();

        header.add("n0", "");
        header.add("n1", ",");
        header.add("n2", ",,");
        header.add("N3", "abc");
        header.add("N4", "def");
        header.add("n5", "abc,def,hig");
        header.add("N6", "abc");
        header.add("n6", "def");
        header.add("N6", "hig");
        header.add("n7", "abc ,  def;q=0.9  ,  hig");
        header.add("n8", "abc ,  def;q=0  ,  hig");
        header.add(HttpHeader.ACCEPT, "abc ,  def;q=0  ,  hig");

        for (int i=0;i<8;i++)
        {
            assertTrue(header.containsKey("n"+i));
            assertTrue(header.containsKey("N"+i));
            assertFalse(""+i,header.contains("n"+i,"xyz"));
            assertEquals(""+i,i>=4,header.contains("n"+i,"def"));
        }


        assertTrue(header.contains(new HttpField("N5","def")));
        assertTrue(header.contains(new HttpField("accept","abc")));
        assertTrue(header.contains(HttpHeader.ACCEPT,"abc"));
        assertFalse(header.contains(new HttpField("N5","xyz")));
        assertFalse(header.contains(new HttpField("N8","def")));
        assertFalse(header.contains(HttpHeader.ACCEPT,"def"));
        assertFalse(header.contains(HttpHeader.AGE,"abc"));

        assertFalse(header.containsKey("n11"));
    }
