    void createDataset() throws SQLException {

        Statement statement = jdbcConnection.createStatement();

        statement.execute("drop table colors if exists; "
                          + "drop table sizes if exists; "
                          + "drop table fruits if exists; "
                          + "drop table trees if exists; ");
        statement.execute("create table colors(id int, val varchar(10)); ");
        statement.execute("insert into colors values(1,'red'); "
                          + "insert into colors values(2,'green'); "
                          + "insert into colors values(3,'orange'); "
                          + "insert into colors values(4,'indigo'); ");
        statement.execute("create table sizes(id int, val varchar(10)); ");
        statement.execute("insert into sizes values(1,'small'); "
                          + "insert into sizes values(2,'medium'); "
                          + "insert into sizes values(3,'large'); "
                          + "insert into sizes values(4,'odd'); ");
        statement.execute(
            "create table fruits(id int, name varchar(20), color_id int); ");
        statement.execute(
            "insert into fruits values(1, 'golden delicious',2); "
            + "insert into fruits values(2, 'macintosh',1); "
            + "insert into fruits values(3, 'red delicious',1); "
            + "insert into fruits values(4, 'granny smith',2); "
            + "insert into fruits values(5, 'tangerine',4);");
        statement.execute(
            "create table trees(id int, name varchar(30), fruit_id int, size_id int); ");
        statement.execute(
            "insert into trees values(1, 'small golden delicious tree',1,1); "
            + "insert into trees values(2, 'large macintosh tree',2,3); "
            + "insert into trees values(3, 'large red delicious tree',3,3); "
            + "insert into trees values(4, 'small red delicious tree',3,1); "
            + "insert into trees values(5, 'medium granny smith tree',4,2); ");
        statement.close();
    }
