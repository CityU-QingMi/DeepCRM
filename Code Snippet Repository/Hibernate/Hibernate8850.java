    @Test
    public void testNaturalIdCriteria() {
        Session s = openSession();
        s.beginTransaction();
        Account u = new Account(new AccountId(1), "testAcct" );
        s.persist( u );
        s.getTransaction().commit();
        s.close();

        s = openSession();
        s.beginTransaction();
        u = ( Account ) s.createCriteria( Account.class )
                .add( Restrictions.naturalId().set( "shortCode", "testAcct" ) )
                .setCacheable( true )
                .uniqueResult();
        assertNotNull( u );
        s.getTransaction().commit();
        s.close();

        s = openSession();
        s.beginTransaction();
        s.createQuery( "delete Account" ).executeUpdate();
        s.getTransaction().commit();
        s.close();
    }
