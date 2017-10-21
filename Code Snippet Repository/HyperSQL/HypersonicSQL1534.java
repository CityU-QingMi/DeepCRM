    public int setString(final long pos, final String str, final int offset,
            final int len) throws SQLException {

        checkClosed();

        if (str == null) {
            throw JDBCUtil.nullArgument("str");
        }

        final int strlen = str.length();

        if (offset < 0 || offset > strlen) {
            throw JDBCUtil.outOfRangeArgument("offset: " + offset);
        }

        if (len < 0 || len > strlen - offset) {
            throw JDBCUtil.outOfRangeArgument("len: " + len);
        }

        if (pos < 1L) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        long oldLength = this.length();

        if (pos > oldLength + 1) {
            fillSpace(oldLength + 1, pos);
        }

        Writer writer = null;
        try {
            writer = setCharacterStream(pos);
            writer.write(str, offset, len);
        } catch (SQLException ex) {
            throw ex;
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (RuntimeException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(writer);
        }
        return len;
    }
