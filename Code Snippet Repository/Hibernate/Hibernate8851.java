    @Test
    public void testByNaturalId() {
        Session s = openSession();
        s.beginTransaction();
        Account u = new Account(new AccountId(1), "testAcct" );
        s.persist( u );
        s.getTransaction().commit();
        s.close();

        s = openSession();
        s.beginTransaction();
        u = ( Account ) s.byNaturalId(Account.class).using("shortCode", "testAcct").load();
        assertNotNull( u );
        s.getTransaction().commit();
        s.close();

        s = openSession();
        s.beginTransaction();
        s.createQuery( "delete Account" ).executeUpdate();
        s.getTransaction().commit();
        s.close();
    }
