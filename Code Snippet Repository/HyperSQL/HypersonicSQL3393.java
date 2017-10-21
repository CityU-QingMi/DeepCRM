    public void test2pSequences() throws Exception {

        String prefix = "public.";

        execSQL("CREATE SEQUENCE " + prefix + "tstseq2", 0);
        execSQL("ALTER SEQUENCE " + prefix + "tstseq RESTART WITH 23", 0);
        assertEquals(1, queryRowCount("SELECT next value FOR " + prefix
                                      + "tstseq FROM tsttbl WHERE i = 1"));
        execSQL("DROP SEQUENCE " + prefix + "tstseq", 0);
    }
