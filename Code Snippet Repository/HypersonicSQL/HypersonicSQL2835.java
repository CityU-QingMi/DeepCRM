    public Object[] getSingleRowData() {

        initialiseNavigator();
        navigator.next();

        Object[] data = navigator.getCurrent();

        data = (Object[]) ArrayUtil.resizeArrayIfDifferent(data,
                metaData.getColumnCount());

        return data;
    }
