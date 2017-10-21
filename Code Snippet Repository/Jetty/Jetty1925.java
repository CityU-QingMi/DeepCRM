    @Test
    public void testLocalReferenceable() throws Exception
    {
        Hashtable<String,String> env1 = new Hashtable<String,String>();
        env1.put("flavour", "orange");
        InitialContext ic1 = new InitialContext(env1);

        ic1.bind("valencia", new Fruit("orange"));

        Object o = ic1.lookup("valencia");

        Hashtable<String,String> env2 = new Hashtable<String,String>();
        InitialContext ic2 = new InitialContext(env2);
        try
        {
            o = ic2.lookup("valencia");
            fail("Constructed object from reference without correct environment");
        }
        catch (Exception e)
        {
            assertEquals("No flavour!", e.getMessage());
        }
    }
