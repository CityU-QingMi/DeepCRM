    public boolean isCaseSensitive(int column) throws SQLException {

        checkColumn(column);

        Type type = translateType(resultMetaData.columnTypes[--column]);

        if (type.isCharacterType()) {
            return type.getCollation().isCaseSensitive();
        }

        return false;
    }
