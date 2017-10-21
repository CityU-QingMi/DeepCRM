    public long position(final Clob pattern,
                         final long start) throws SQLException {

        long patternLength;

        if (start < 1) {
            throw JDBCUtil.outOfRangeArgument("start: " + start);
        } else if ((patternLength = pattern == null ? 0
                                                    : pattern.length()) == 0) {
            return -1L;
        } else if (patternLength > Integer.MAX_VALUE) {
            throw JDBCUtil.outOfRangeArgument("pattern.length(): "
                                          + patternLength);
        }

        long length = this.length();

        if (start > length || patternLength > length || start > length
                - patternLength) {
            return -1;
        }

        String stringPattern;

        if (pattern instanceof JDBCClob) {
            stringPattern = ((JDBCClob) pattern).getData();
        } else {
            Reader          reader = null;
            StringWriter writer = new StringWriter();

            try {
                reader = pattern.getCharacterStream();

                InOutUtil.copy(reader, writer, patternLength);
            } catch (IOException ex) {
                throw JDBCUtil.sqlException(ex);
            } catch (RuntimeException ex) {
                throw JDBCUtil.sqlException(ex);
            } finally {
                closeSafely(reader);
            }

           stringPattern = writer.toString();
        }

        return position0(stringPattern, start);
    }
