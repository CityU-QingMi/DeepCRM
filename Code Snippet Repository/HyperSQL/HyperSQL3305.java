    void addRow(String[] Name, int[] type, Object[] Values,
                int nbColumns) throws Exception {

        if ((Name.length != type.length) || (Name.length != Values.length)
                || (Name.length != (nbColumns + 1))) {
            throw new Exception("Size of parameter incoherent");
        }

        if (sColumnNames == null) {
            iColumnCount = nbColumns;
            sColumnNames = Name;
            iColumnTypes = type;

            vRows.addElement(null);
        }

        if ((iMaxRowIdx > 0) && (this.getColumnCount() != nbColumns)) {
            throw new Exception("Wrong number of columns: "
                                + this.getColumnCount()
                                + " column is expected");
        }

        iMaxRowIdx++;

        vRows.addElement(Values);
    }
