    public static ResultMetaData newParameterMetaData(int colCount) {

        ResultMetaData md = new ResultMetaData(PARAM_METADATA);

        md.columnTypes         = new Type[colCount];
        md.columnLabels        = new String[colCount];
        md.paramModes          = new byte[colCount];
        md.paramNullable       = new byte[colCount];
        md.columnCount         = colCount;
        md.extendedColumnCount = colCount;

        return md;
    }
