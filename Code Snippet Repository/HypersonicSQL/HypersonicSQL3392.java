    public void test2pViews() throws Exception {

        String prefix = "public.";

        assertEquals(2, queryRowCount("SELECT i FROM " + prefix
                                      + "tstview WHERE i IN (1, 2, 3)"));
        assertEquals(2, queryRowCount("SELECT i FROM tstview"));
        assertEquals(2, queryRowCount("SELECT ali.i FROM tstview ali"));
        assertEquals("Sub-query", 1,
                     queryRowCount("SELECT vc FROM " + prefix
                                   + "tstview WHERE i = (\n"
                                   + "    SELECT i2 FROM " + prefix
                                   + "joinedtbl\n" + ")"));
        assertEquals("Join", 1,
                     queryRowCount("SELECT vc FROM " + prefix + "tstview, "
                                   + prefix + "joinedtbl\n"
                                   + "WHERE tstview.i = joinedtbl.i2\n"
                                   + "AND joinedtbl.vc2 = 'zwei'"));
        assertEquals(
            2, queryRowCount(
                "SELECT i FROM " + prefix
                + "tstview ali WHERE ali.i IN (1, 2, 3)"));

        // view
        execSQL("CREATE VIEW " + prefix
                + "tstview2 AS SELECT * FROM tsttbl WHERE i < 10", 0);

        // grant, revoke
        execSQL("GRANT ALL ON " + prefix + "tstview TO tstuser", 0);
        execSQL("REVOKE ALL ON " + prefix + "tstview FROM tstuser RESTRICT",
                0);

        // drop
        execSQL("DROP VIEW tstview", 0);
    }
