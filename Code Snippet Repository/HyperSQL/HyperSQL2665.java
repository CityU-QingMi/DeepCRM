    public void testBasicQueries() throws Exception {

        String prefix = "public.";

        assertEquals(2, queryRowCount("SELECT i FROM " + prefix + "tsttbl"));
        assertEquals(1, queryRowCount("SELECT vc FROM " + prefix
                                      + "tsttbl WHERE i = 1"));
        assertEquals(1, queryRowCount("SELECT vc FROM " + prefix
                                      + "tsttbl WHERE i = (\n"
                                      + "    SELECT i2 FROM " + prefix
                                      + "joinedtbl\n" + ")"));
    }
