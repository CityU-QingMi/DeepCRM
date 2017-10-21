    public java.io.InputStream getAsciiStream(
            int columnIndex) throws SQLException {

        String s = getString(columnIndex);

        if (s == null) {
            return null;
        }

        try {
            return new ByteArrayInputStream(s.getBytes("US-ASCII"));
        } catch (IOException e) {
            return null;
        }
    }
