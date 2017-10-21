    public void testStreams() {

        try {
            String ddl0 = "DROP TABLE BSTREAM IF EXISTS";
            String ddl1 =
                "CREATE TABLE BSTREAM(A INT IDENTITY PRIMARY KEY, B VARBINARY(20))";

            statement.execute(ddl0);
            statement.execute(ddl1);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("ddl failure");
        }

        try {
            String            dml0 = "insert into bstream values(default, ?)";
            String            dql0 = "select * from bstream where a = ?";
            PreparedStatement ps1  = connection.prepareStatement(dml0);
            PreparedStatement ps2  = connection.prepareStatement(dql0);
            byte[]            data = new byte[] {
                0x10, 0x11, 0x12, 0x13, 0x14, 0x15
            };
            InputStream       is   = new ByteArrayInputStream(data);

            ps1.setBinaryStream(1, is);
            ps1.execute();
            ps1.setObject(1, data);
            ps1.execute();
            ps2.setInt(1, 1);

            ResultSet rs = ps2.executeQuery();

            rs.next();

            InputStream isr = rs.getBinaryStream(2);

            for (int i = 0; i < data.length; i++) {
                int val = isr.read();

                assertTrue(val == data[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("dml failure");
        }
    }
