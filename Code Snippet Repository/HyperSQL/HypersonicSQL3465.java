    public void testSectionFive() throws Exception {

        Connection conn = newConnection();
        PreparedStatement ps =
            conn.prepareStatement("insert into tident (c2) values ?");

        for (int i = 0; i < 20; i++) {
            ps.setString(1, String.valueOf(i));
            ps.executeUpdate();
        }

        ps.close();

        ps = conn.prepareStatement("insert into tsingle (c1) values ?");

        for (int i = 0; i < 20; i++) {
            ps.setInt(1, i + 7);
            ps.executeUpdate();
        }

        ps.close();

        Statement st = conn.createStatement();

        st.execute("SHUTDOWN IMMEDIATELY");
        partD();

        conn = newConnection();
        st   = conn.createStatement();

        st.execute("insert into tident values default, 'dont know'");

        int count = st.executeUpdate("update tident set c2 = c2 || ' '");

        assertEquals("identity table count mismatch", 21, count);

        ResultSet rs = st.executeQuery("select count(*) from tsingle");

        assertTrue(rs.next());
        assertEquals(20, rs.getInt(1));
        st.execute("set table tsingle read only");
        st.execute("SHUTDOWN SCRIPT");

        conn = newConnection();
        st   = conn.createStatement();

        st.execute("SHUTDOWN SCRIPT");
    }
