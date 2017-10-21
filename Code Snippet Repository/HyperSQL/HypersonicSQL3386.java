    protected void setUp() throws Exception {

        Class.forName("org.hsqldb.jdbc.JDBCDriver");

        con = DriverManager.getConnection("jdbc:hsqldb:mem:parsetest", "sa",
                                          "");
        statement = con.createStatement();

        execSQL("SET AUTOCOMMIT false", 0);
        execSQL("CREATE TABLE tsttbl (i INT, vc VARCHAR(100))", 0);
        execSQL(
            "CREATE TABLE bigtbl (i INT, vc VARCHAR(100), i101 INT, i102 INT, "
            + "i103 INT, i104 INT, i105 INT, i106 INT, i107 INT, "
            + "i108 INT, i109 INT, i110 INT, i111 INT, i112 INT, "
            + "i113 INT, i114 INT, i115 INT, i116 INT, i117 INT, "
            + "i118 INT, i119 INT)", 0);
        execSQL("INSERT INTO tsttbl VALUES (1, 'one')", 1);
        execSQL("INSERT INTO tsttbl VALUES (2, 'two')", 1);
        execSQL("CREATE TABLE joinedtbl (i2 INT, vc2 VARCHAR(100))", 0);
        execSQL("INSERT INTO joinedtbl VALUES (2, 'zwei')", 1);
        execSQL("CREATE TABLE indexedtbl (i3 INT, vc3 VARCHAR(100))", 0);
        execSQL("INSERT INTO indexedtbl VALUES (3, 'tres')", 1);
        execSQL("CREATE TABLE triggedtbl (i4 INT, vc4 VARCHAR(100))", 0);

        // Can't test text tables in memory-only DB.
        //execSQL("CREATE TEXT TABLE texttbl (i5 INT, vc5 VARCHAR(100))", 0);
        execSQL("INSERT INTO triggedtbl VALUES (4, 'quatro')", 1);
        execSQL("CREATE FUNCTION tstali(VARCHAR(100)) RETURNS VARCHAR(100) "
                + "LANGUAGE JAVA EXTERNAL NAME "
                + "'CLASSPATH:org.hsqldb.test.BlaineTrig.capitalize'", 0);
        execSQL("CREATE UNIQUE INDEX tstind ON indexedtbl (i3)", 0);
        execSQL("CREATE SEQUENCE tstseq", 0);
        execSQL("CREATE TRIGGER tsttrig AFTER INSERT ON triggedtbl CALL \""
                + "org.hsqldb.test.BlaineTrig\"", 0);
        execSQL("CREATE USER tstuser PASSWORD fake", 0);
        execSQL("CREATE TABLE constrainedtbl (i6 INT, vc6 VARCHAR(100), "
                + "CONSTRAINT ucons UNIQUE(i6))", 0);
        execSQL("CREATE TABLE primarytbl (i8 INT, i18 INT, vc8 VARCHAR(100), "
                + "UNIQUE(i8), UNIQUE(i18))", 0);
        execSQL(
            "CREATE TABLE foreigntbl (i7 INT, vc7 VARCHAR(100), "
            + "CONSTRAINT tstfk FOREIGN KEY (i7) REFERENCES primarytbl (i8))", 0);
        execSQL("CREATE TABLE playtbl (i9 INT, vc9 VARCHAR(100))", 0);
        execSQL("CREATE TABLE toindextbl (i10 INT, vc10 VARCHAR(100))", 0);
        execSQL("INSERT INTO toindextbl VALUES (10, 'zehn')", 1);

        // Do the view last since it can cause dependendies with indexes, etc.
        execSQL("CREATE VIEW tstview AS SELECT * FROM tsttbl WHERE i < 10", 0);
        execSQL("COMMIT", 0);
    }
