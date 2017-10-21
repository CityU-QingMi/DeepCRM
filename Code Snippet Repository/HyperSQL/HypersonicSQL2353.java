    Object[] projectData(Object[] data, int[] columnMap) {

        if (columnMap == null) {
            data = (Object[]) ArrayUtil.resizeArrayIfDifferent(data,
                    visibleColumnCount);
        } else {
            Object[] newData = new Object[visibleColumnCount];

            ArrayUtil.projectRow(data, columnMap, newData);

            data = newData;
        }

        return data;
    }
