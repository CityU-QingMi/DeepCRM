    public String getSubString(final long pos,
                               final int length) throws SQLException {

        Reader          reader = null;
        CharArrayWriter writer;
        final int       initialCapacity =
                Math.min(InOutUtil.DEFAULT_COPY_BUFFER_SIZE, length);

        try {
            reader = getCharacterStream(pos, length);
            writer = new CharArrayWriter(initialCapacity);

            InOutUtil.copy(reader, writer, length);

            return writer.toString();
        } catch (SQLException ex) {
            throw ex;
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(reader);
        }
    }
