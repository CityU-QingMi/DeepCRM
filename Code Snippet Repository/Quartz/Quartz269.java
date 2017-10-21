    @Override
    protected void setBytes(PreparedStatement ps, int index, ByteArrayOutputStream baos)
            throws SQLException {
        
        byte[] byteArray;
        if (baos == null) {
            //saving 0 byte blob may cause error? like http://dev.naver.com/projects/cubrid/issue/13710 - (0 byte bit)
            //alternativly store null since blob not null columns are not allowed (cubrid 8.4.1). may be allowed in future versions?
            byteArray = new byte[0];
        } else {
            byteArray = baos.toByteArray();
        }

        //quartz 2.x uses c3p0, c3p0 doesn't support createBlob method as of 0.9.2        
        Connection conn = ps.getConnection();
        if (conn instanceof C3P0ProxyConnection) {
            try {
                C3P0ProxyConnection c3p0Conn = (C3P0ProxyConnection) conn;
                Method m = Connection.class.getMethod("createBlob", new Class[]{}); //will call createBlob method on the underlying connection
                Object[] args = new Object[]{}; //arguments to be passed to the method. none in this case
                Blob blob = (Blob) c3p0Conn.rawConnectionOperation(m, C3P0ProxyConnection.RAW_CONNECTION, args); 
                blob.setBytes(1, byteArray);
                ps.setBlob(index, blob);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Blob blob = ps.getConnection().createBlob();
            blob.setBytes(1, byteArray);
            ps.setBlob(index, blob);
        }
    }
