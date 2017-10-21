    public void testIdentity() {

        boolean valid = false;

        try {
            Statement s = con.createStatement();

            s.execute("drop table a if exists");
            s.execute("create cached table a (a int identity, b int)");

            PreparedStatement p1 =
                con.prepareStatement("insert into a(b) values ?");

            p1.setInt(1, 10);
            p1.executeUpdate();

            PreparedStatement p2 = con.prepareStatement("call identity()");
            ResultSet         r  = p2.executeQuery();

            while (r.next()) {
                r.getInt(1);

                valid = true;
            }

            p1.setInt(1, 11);
            p1.executeUpdate();

            PreparedStatement ps3 = con.prepareStatement(
                "select count(*) from a where a in ((select a from a where b = ?) union (select ? from a))");

            ps3.setInt(1, 10);
            ps3.setInt(2, 1);

            r = ps3.executeQuery();

            while (r.next()) {
                int value = r.getInt(1);

                valid = value == 2;
            }

            assertTrue(valid);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
