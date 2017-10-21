    @Test
    @TestForIssue( jiraKey = "" )
    public void test() {
        Triggerable triggerable = logInspection.watchForLogMessages( "HHH000352" );
        triggerable.reset();

        StatelessSession session = entityManagerFactory().unwrap( SessionFactory.class ).openStatelessSession();
        Transaction tx = session.beginTransaction();

        try {
            Employee employee = new Employee( "1", "2", 1 );
            employee.setId( 1 );
            session.insert( employee );

            tx.rollback();
        }
        catch (HibernateException e) {
            if ( tx != null ) {
                tx.rollback();
            }
        }
        finally {
            session.close();
            assertFalse( triggerable.wasTriggered() );
        }
    }
