    public int getSize(Row row) {

        reset();

        try {
            writeSize(0);
            writeData(row, row.getTable().getColumnTypes());
            writeEnd();
        } catch (Exception e) {
            reset();

//            throw Error.error(ErrorCode.FILE_IO_ERROR, e.toString());
        }

        int rowsize = size();

        reset();

        return rowsize;
    }
