    public void addRow(Object[] r) {

        Object[] row = new Object[r.length];

        // System.arraycopy(r, 0, row, 0, r.length);
        for (int i = 0; i < r.length; i++) {
            row[i] = r[i];

            if (row[i] == null) {

//                row[i] = "(null)";
            }
        }

        rows.addElement(row);
    }
