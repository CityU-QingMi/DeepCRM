    public java.io.InputStream getUnicodeStream(
            int columnIndex) throws SQLException {

        String s = getString(columnIndex);

        if (s == null) {
            return null;
        }

        return new StringInputStream(s);
    }
