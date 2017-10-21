    @Override
    protected void cleanupTest() {
        Session s = openSession();
        s.beginTransaction();
        s.createQuery( "delete from Transport" );
        s.createQuery( "delete from Tour" );
        s.createQuery( "delete from Node" );
        s.createQuery( "delete from Route" );
        s.createQuery( "delete from Vehicle" );
    }
