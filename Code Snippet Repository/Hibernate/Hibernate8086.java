    private void createTestData() {
        doInHibernate( this::sessionFactory, session -> {

            final Customer customer = new Customer( 1, "Acme" );
            session.save( customer );
            session.save( new User( 1, "steve", customer ) );
            session.save( new User( 2, "jane" ) );
            session.save( new FinancialRecord( 1, customer, "steve" ) );
            session.save( new FinancialRecord( 2, customer, null ) );

        } );
    }
