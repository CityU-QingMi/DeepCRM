    private void executeMerge(String merge) throws SQLException {

        // create table T and insert some preliminary data
        stmnt.execute("DROP SCHEMA SA IF EXISTS CASCADE;");
        stmnt.execute("CREATE SCHEMA SA AUTHORIZATION SA");
        stmnt.execute("DROP TABLE SA.T IF EXISTS;");
        stmnt.execute(
            "CREATE TABLE SA.T (I IDENTITY, A CHAR(10), B CHAR(10));");
        stmnt.execute("INSERT INTO SA.T VALUES ((0, 'A', 'a'),"
                      + "(1, 'B', 'b'), (4, 'C', 'c'));");

        // create table S and insert some preliminary data
        stmnt.execute("DROP TABLE SA.S IF EXISTS;");
        stmnt.execute(
            "CREATE TABLE SA.S (I IDENTITY, A CHAR(10), B CHAR(10), C CHAR(10));");
        stmnt.execute(
            "INSERT INTO SA.S VALUES ((0, 'D', 'd', 'Dd'),"
            + "(2, 'E', 'e', 'Ee'), (3, 'F', 'f', 'Ff'), (4, 'G', 'g', 'Gg'));");
        printTable("SA.T", "*", 3);
        printTable("SA.S", "*", 4);
        stmnt.execute(merge);
    }
