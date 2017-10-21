    public void addRow(String key, String value, String state, int color) {

        String[] row = new String[4];

        if (value == null) {
            value = "";
        }

        row[0] = key;
        row[1] = value;
        row[2] = state;    // null / "-" / "+"
        row[3] = String.valueOf(color);

        vData.addElement(row);

        int len = fMetrics.stringWidth(value);

        if (len > iMaxTextLength) {
            iMaxTextLength = len;
        }

        iRowCount++;
    }
