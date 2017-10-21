    public int getRealSize(RowOutputInterface out) {

        RowOutputBinary bout = (RowOutputBinary) out;
        int             size = out.getSize(this);

        if (updateData != null) {
            size += bout.getSize(updateData, targetTable.getColumnCount(),
                                 targetTable.getColumnTypes());

            if (updateColMap != null) {
                size += bout.getSize(updateColMap);
            }
        }

        return size;
    }
