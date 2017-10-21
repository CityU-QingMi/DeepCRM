    public void test2pIndexes() throws Exception {

        String prefix = "public.";

        execSQL("CREATE UNIQUE INDEX playind ON playtbl (i9)", 0);
        execSQL("CREATE UNIQUE INDEX bigind ON bigtbl (i)", 0);
        execSQL("CREATE UNIQUE INDEX " + prefix + "tstind2 ON tsttbl (i)", 0);
        execSQL("ALTER INDEX " + prefix + "playind RENAME TO renamedind", 0);
        execSQL("ALTER INDEX " + prefix + "renamedind RENAME TO " + prefix
                + "tstind22", 0);
        execSQL("ALTER INDEX tstind RENAME TO " + prefix + "renamedind", 0);
        execSQL("DROP INDEX " + prefix + "bigind", 0);
    }
