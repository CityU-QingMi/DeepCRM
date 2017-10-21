    private void fillSpace(final long startPos, long endPos)
            throws SQLException {

        Writer writer = null;
        try {
            writer = setCharacterStream(startPos);
            for(long i = endPos - startPos ; i >= 0; i--) {
                writer.append(' ');
            }
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (RuntimeException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(writer);
        }
    }
