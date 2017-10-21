    @Test
    public void testLocalEnvironment() throws Exception
    {
        Hashtable<String,String> env1 = new Hashtable<String,String>();
        env1.put("make", "holden");
        env1.put("model", "commodore");

        Object car1 = new Object();

        InitialContext ic = new InitialContext(env1);
        ic.bind("car1", car1);
        assertNotNull(ic.lookup("car1"));
        assertEquals(car1, ic.lookup("car1"));

        Context carz = ic.createSubcontext("carz");
        assertNotNull(carz);
        Hashtable ht = carz.getEnvironment();
        assertNotNull(ht);
        assertEquals("holden", ht.get("make"));
        assertEquals("commodore", ht.get("model"));

        Hashtable<String,String> env2 = new Hashtable<String,String>();
        env2.put("flavour", "strawberry");
        InitialContext ic2 = new InitialContext(env2);
        assertEquals(car1, ic2.lookup("car1"));
        Context c = (Context)ic2.lookup("carz");
        assertNotNull(c);
        ht = c.getEnvironment();
        assertEquals("holden", ht.get("make"));
        assertEquals("commodore", ht.get("model"));

        Context icecreamz = ic2.createSubcontext("icecreamz");
        ht = icecreamz.getEnvironment();
        assertNotNull(ht);
        assertEquals("strawberry", ht.get("flavour"));

        Context hatchbackz = ic2.createSubcontext("carz/hatchbackz");
        assertNotNull(hatchbackz);
        ht = hatchbackz.getEnvironment();
        assertNotNull(ht);
        assertEquals("holden", ht.get("make"));
        assertEquals("commodore", ht.get("model"));
        assertEquals(null, ht.get("flavour"));

        c = (Context)ic.lookup("carz/hatchbackz");
        assertNotNull(c);
        assertEquals(hatchbackz, c);

    }
