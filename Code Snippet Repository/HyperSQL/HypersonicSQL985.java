    public ResultMetaData getParametersMetaData() {

        ResultMetaData meta = super.getParametersMetaData();

        for (int i = 0; i < meta.columnLabels.length; i++) {
            ColumnSchema param = parameters[i].getColumn();

            if (param != null && param.getName() != null) {
                meta.columnLabels[i] = param.getNameString();
            }
        }

        return meta;
    }
