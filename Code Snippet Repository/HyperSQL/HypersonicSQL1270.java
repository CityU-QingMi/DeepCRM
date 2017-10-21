    public String streamToString(InputStream is, String cs)
            throws IOException {
        byte[] ba = null;
        int bytesread = 0;
        int retval;
        try {
            try {
                ba = new byte[is.available()];
            } catch (RuntimeException re) {
                throw new IOException(SqltoolRB.read_toobig.getString());
            }
            while (bytesread < ba.length &&
                    (retval = is.read(
                            ba, bytesread, ba.length - bytesread)) > 0) {
                bytesread += retval;
            }
            if (bytesread != ba.length)
                throw new IOException(
                        SqltoolRB.read_partial.getString(bytesread, ba.length));
            try {
                return (cs == null) ? (new String(ba))
                                         : (new String(ba, cs));
            } catch (UnsupportedEncodingException uee) {
                throw new IOException(
                        SqltoolRB.encode_fail.getString(uee));
            } catch (RuntimeException re) {
                throw new IOException(SqltoolRB.read_convertfail.getString());
            }
        } finally {
            try {
                is.close();
            } catch (IOException ioe) {
                // intentionally empty
            } finally {
                is = null;  // Encourage GC of buffers.
                // Modification of input param will elicit a compiler warning.
                // N.b. the caller reference will remain non-null.
            }
        }
    }
