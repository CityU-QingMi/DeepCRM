    @TestForIssue(jiraKey = "")
    @Test
    public void testNaturalIdCriteria() {
        Session s = openSession();
        s.beginTransaction();
        A u = new A(new AId(1), "testCode" );
        s.persist( u );
        s.getTransaction().commit();
        s.close();

        s = openSession();
        s.beginTransaction();
        u = ( A ) s.createCriteria( A.class )
                .add( Restrictions.naturalId().set( "shortCode", "testCode" ) )
                .uniqueResult();
        assertNotNull( u );
        s.getTransaction().commit();
        s.close();

        s = openSession();
        s.beginTransaction();
        s.createQuery( "delete A" ).executeUpdate();
        s.getTransaction().commit();
        s.close();
    }
