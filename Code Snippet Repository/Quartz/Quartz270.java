    @Override
    protected Object getObjectFromBlob(ResultSet rs, String colName) throws ClassNotFoundException, IOException, SQLException {
        Blob blob = rs.getBlob(colName);
        if (blob == null) {
            return null;
        } else {
            try {
                if (blob.length() == 0) {
                    return null;
                } else {
                    InputStream binaryInput = blob.getBinaryStream();
                    if (binaryInput == null) {
                        return null;
                    } else if (binaryInput instanceof ByteArrayInputStream && ((ByteArrayInputStream) binaryInput).available() == 0 ) {
                        return null;
                    } else {
                        ObjectInputStream in = new ObjectInputStream(binaryInput);
                        try {
                            return in.readObject();
                        } finally {
                            in.close();
                        }
                    }
                }
            } finally {
                blob.free();
            }
        }
    }
