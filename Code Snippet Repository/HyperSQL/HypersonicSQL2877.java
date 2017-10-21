    private static int encodeTableColumnAttrs(ColumnBase column) {

        int out = column.getNullability();    // always between 0x00 and 0x02

        if (column.isIdentity()) {
            out |= 0x00000004;
        }

        if (column.isWriteable()) {
            out |= 0x00000008;
        }

        if (column.isSearchable()) {
            out |= 0x00000010;
        }

        return out;
    }
