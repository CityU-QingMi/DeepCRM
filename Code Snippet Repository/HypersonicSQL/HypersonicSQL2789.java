    public void setHeader(String header) {

        if (textFileSettings.ignoreFirst && fileFreePosition == 0) {
            try {
                writeHeader(header);

                this.header = header;
            } catch (HsqlException e) {
                throw new HsqlException(
                    e, Error.getMessage(ErrorCode.GENERAL_IO_ERROR),
                    ErrorCode.GENERAL_IO_ERROR);
            }

            return;
        }

        throw Error.error(ErrorCode.TEXT_TABLE_HEADER);
    }
