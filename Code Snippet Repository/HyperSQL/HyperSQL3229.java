    public void addRow(String[] data) {

        if (data.length != iColCount) {
            return;
        }

        String[] row = new String[iColCount];

        for (int i = 0; i < iColCount; i++) {
            row[i] = data[i];

            if (row[i] == null) {
                row[i] = "(null)";
            }
        }

        vData.addElement(row);

        iRowCount++;
    }
