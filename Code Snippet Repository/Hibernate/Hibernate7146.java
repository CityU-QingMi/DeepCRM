    @Test
    public void test() {
        // Delete the Parent
        doInHibernate( this::sessionFactory, s -> {
            Parent loadedParent = (Parent) s.createQuery( "SELECT p FROM Parent p WHERE name=:name" )
                    .setParameter( "name", "PARENT" )
                    .uniqueResult();

            s.delete( loadedParent );
        } );
        // If the lazy relation is not fetch on cascade there is a constraint violation on commit
    }
