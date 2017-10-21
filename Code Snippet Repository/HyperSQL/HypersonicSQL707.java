    public void write(RowOutputInterface out) {

        writeNodes(out);

        if (hasDataChanged) {
            out.writeData(this, table.colTypes);

            if (updateData != null) {
                Type[] targetTypes = targetTable.colTypes;

                out.writeData(targetTypes.length, targetTypes, updateData,
                              null, null);

                RowOutputBinary bout = (RowOutputBinary) out;

                if (updateColMap == null) {
                    bout.writeNull(Type.SQL_ARRAY_ALL_TYPES);
                } else {
                    bout.writeArray(updateColMap);
                }
            }

            out.writeEnd();

            hasDataChanged = false;
        }
    }
