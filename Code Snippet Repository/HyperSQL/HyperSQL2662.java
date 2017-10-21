    public void test2pConstraints() throws Exception {

        String prefix = "public.";

        // Some named constraints
        execSQL("CREATE TABLE constbl1 (i11 INT, vc12 VARCHAR(100), "
                + "CONSTRAINT " + prefix + "uconsw UNIQUE(vc12))", 0);
        execSQL("CREATE TABLE constbl2 (i11 INT, vc12 VARCHAR(100), "
                + "CONSTRAINT " + prefix + "chk CHECK (i11 > 4))", 0);
        execSQL("CREATE TABLE for2tbl (i7 INT, vc7 VARCHAR(100), "
                + "CONSTRAINT " + prefix
                + "tstfk2 FOREIGN KEY (i7) REFERENCES primarytbl (i8))", 0);
        execSQL("CREATE TABLE for3tbl (i7 INT, vc7 VARCHAR(100), "
                + "CONSTRAINT " + prefix + "tstpk2 PRIMARY KEY (i7))", 0);
        execSQL("ALTER TABLE constrainedtbl ADD CONSTRAINT " + prefix
                + "con1 CHECK (i6 > 4)", 0);
        execSQL("ALTER TABLE foreigntbl ADD CONSTRAINT " + prefix
                + "tstfkm FOREIGN KEY "
                + "(i7) REFERENCES primarytbl (i18)", 0);
        execSQL("ALTER TABLE for3tbl DROP CONSTRAINT " + prefix + "tstpk2", 0);
    }
