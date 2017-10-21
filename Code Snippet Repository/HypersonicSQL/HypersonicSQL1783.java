    public static SQLException sqlException(Throwable t) {

//#ifdef JAVA6
        return new SQLNonTransientConnectionException(t);

//#else
/**/
/**/
/**/
/**/
/**/

//#endif JAVA6
    }
