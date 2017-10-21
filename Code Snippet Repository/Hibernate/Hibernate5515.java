    @Test
    public void testPersistExisting() throws Exception {
        EntityManager em = getOrCreateEntityManager();
        em.getTransaction().begin();
        Wallet w = new Wallet();
        w.setBrand( "Lacoste" );
        w.setModel( "Minimic" );
        w.setSerial( "0100202002" );
        em.persist( w );
        w = new Wallet();
        w.setBrand( "Lacoste" );
        w.setModel( "Minimic" );
        w.setSerial( "0100202002" );
        try {
            em.persist( w );
        }
        catch ( EntityExistsException eee ) {
            //success
            if ( em.getTransaction() != null ) {
                em.getTransaction().rollback();
            }
            em.close();
            return;
        }
        try {
            em.getTransaction().commit();
            fail( "Should have raised an exception" );
        }
        catch ( PersistenceException pe ) {
        }
        finally {
            em.close();
        }
    }
