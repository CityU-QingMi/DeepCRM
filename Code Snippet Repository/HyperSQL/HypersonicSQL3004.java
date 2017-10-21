    protected BlobData readBlob() {

        readNumberField(Type.SQL_BIGINT);

        if (value == null) {
            return null;
        }

        long id = ((Number) value).longValue();

        return new BlobDataID(id);
    }
