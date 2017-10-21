    public static short getIdForColumn(int colIndex, ResultMetaData md) {
        if (!md.isTableColumn(colIndex)) {
            return 0;
        }
        short hashCode =
            (short) md.getGeneratedColumnNames()[colIndex].hashCode();
        if (hashCode < 0) {
            hashCode *= -1;
        }
        return hashCode;
        //return (short) (colIndex + 1);
    }
