    public static ResultMetaData newUpdateResultMetaData(Type[] types) {

        ResultMetaData md = new ResultMetaData(UPDATE_RESULT_METADATA);

        md.columnTypes         = new Type[types.length];
        md.columnCount         = types.length;
        md.extendedColumnCount = types.length;

        ArrayUtil.copyArray(types, md.columnTypes, types.length);

        return md;
    }
