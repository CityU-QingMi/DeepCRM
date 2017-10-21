    public void testUnionSubquery() throws Exception {

        Statement st = connection.createStatement();

        st.execute("DROP TABLE t1 if exists;");
        st.execute("DROP TABLE t2 if exists;");
        st.execute(
            "CREATE TABLE t1 (id int not null, v1 int, v2 int, primary key(id))");
        st.execute(
            "CREATE TABLE t2 (id int not null, v1 int, v3 int, primary key(id))");
        st.execute("INSERT INTO t1 values(1,1,1)");
        st.execute("INSERT INTO t1 values(2,2,2)");
        st.execute("INSERT INTO t2 values(1,3,3)");

        ResultSet rs = st.executeQuery(
            "select t as atable, a as idvalue, b as value1, c as value2, d as value3 from("
            + "(select 't1' as t, t1.id as a, t1.v1 as b, t1.v2 as c, null as d from t1) union"
            + "(select 't2' as t, t2.id as a, t2.v1 as b, null as c, t2.v3 as d from t2)) order by atable, idvalue");

        assertTrue(rs.next());
        assertEquals("t1", rs.getObject("atable"));
        assertEquals(1, rs.getInt("idvalue"));
        assertEquals(1, rs.getInt("value1"));
        assertEquals(1, rs.getInt("value2"));
        assertEquals(null, rs.getObject("value3"));
        assertTrue(rs.next());
        assertEquals("t1", rs.getObject("atable"));
        assertEquals(2, rs.getInt("idvalue"));
        assertEquals(2, rs.getInt("value1"));
        assertEquals(2, rs.getInt("value2"));
        assertEquals(null, rs.getObject("value3"));
        assertTrue(rs.next());
        assertEquals("t2", rs.getObject("atable"));
        assertEquals(1, rs.getInt("idvalue"));
        assertEquals(3, rs.getInt("value1"));
        assertEquals(null, rs.getObject("value2"));
        assertEquals(3, rs.getInt("value3"));    //this fails!
        assertFalse(rs.next());
    }
