    public void testBlobI() {

        System.out.println("Starting (sub-)test: " + getName());

        try {
            Statement st = connection.createStatement();

            st.executeUpdate("drop table BLOBTEST if exists");
            st.executeUpdate("create table BLOBTEST (BT_BLOB BLOB)");
            System.out.println("Running insert...");

            PreparedStatement insert =
                connection.prepareStatement("insert into BLOBTEST values(?)");

            insert.setBytes(1, new byte[]{});
            insert.executeUpdate();
            System.out.println("Running update...");

            PreparedStatement update = connection.prepareStatement(
                "update BLOBTEST set BT_BLOB = CONCAT(BT_BLOB,?)");

            update.setBytes(1, new byte[] {
                1, 2, 3
            });
            update.executeUpdate();
            System.out.println("Running select...");

            PreparedStatement select =
                connection.prepareStatement("select BT_BLOB from BLOBTEST");
            ResultSet result = select.executeQuery();

            System.out.println("Results: " + result.getFetchSize());

            while (result.next()) {
                byte[] data = result.getBytes(1);
                String s    = data == null ? "null"
                                           : String.valueOf(data.length);

                System.out.println("Result: " + s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("test failure");
        }
    }
