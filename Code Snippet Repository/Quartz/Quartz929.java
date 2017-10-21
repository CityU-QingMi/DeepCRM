    @Override
    protected Blob writeDataToBlob(ResultSet rs, int column, byte[] data) throws SQLException {
        Blob blob = rs.getBlob(column);
        
        if (blob == null) { 
            throw new SQLException("Driver's Blob representation is null!");
        }
        
        // handle thin driver's blob
        if (blob instanceof weblogic.jdbc.vendor.oracle.OracleThinBlob) { 
            ((weblogic.jdbc.vendor.oracle.OracleThinBlob) blob).putBytes(1, data);
            return blob;
        } else if(blob.getClass().getPackage().getName().startsWith("weblogic.")) {
            // (more slowly) handle blob for wrappers of other variations of drivers...
            try {
                // try to find putBytes method...
                Method m = blob.getClass().getMethod("putBytes", new Class[] {long.class, byte[].class});
                m.invoke(blob, new Object[] {new Long(1), data});
            } catch (Exception e) {
                try {
                    // Added this logic to the original code from OpenSymphony
                    // putBytes method does not exist. Try setBytes
                    Method m = blob.getClass().getMethod("setBytes", new Class[] { long.class, byte[].class });
                    m.invoke(blob, new Object[] { new Long(1), data });
                } catch (Exception e2) {
                    throw new SQLException("Unable to find putBytes(long,byte[]) or setBytes(long,byte[]) methods on blob: " + e2);
                }
            }
            return blob;
        } else {
            return super.writeDataToBlob(rs, column, data);
        }
    }
