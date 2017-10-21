    public Object getValueAt(int row, int col) {

        if (row >= rows.size()) {
            return null;
        }

        Object[] colArray = (Object[]) rows.elementAt(row);

        if (col >= colArray.length) {
            return null;
        }

        return colArray[col];
    }
