    public static IOException toIOException(Throwable t) {

        if (t instanceof IOException) {
            return (IOException) t;
        }

//#ifdef JAVA6
        return new IOException(t);

//#else
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/

//#endif JAVA6
    }
