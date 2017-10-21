    @SuppressWarnings("")
    protected Blob writeDataToBlob(ResultSet rs, int column, byte[] data) throws SQLException {

        Blob blob = rs.getBlob(column); // get blob

        if (blob == null) { 
            throw new SQLException("Driver's Blob representation is null!");
        }
        
        if (blob instanceof oracle.sql.BLOB) { // is it an oracle blob?
            ((oracle.sql.BLOB) blob).putBytes(1, data);
            ((oracle.sql.BLOB) blob).trim(data.length);
            return blob;
        } else {
            throw new SQLException(
                    "Driver's Blob representation is of an unsupported type: "
                            + blob.getClass().getName());
        }
    }
