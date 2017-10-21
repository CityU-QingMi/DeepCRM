    public static ResultMetaData newSimpleResultMetaData(Type[] types) {

        ResultMetaData md = new ResultMetaData(SIMPLE_RESULT_METADATA);

        md.columnTypes         = types;
        md.columnCount         = types.length;
        md.extendedColumnCount = types.length;

        return md;
    }
