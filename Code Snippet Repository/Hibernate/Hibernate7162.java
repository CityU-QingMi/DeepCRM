    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            s.refresh( myBar );
            Assert.assertFalse( myBar.foos.isEmpty() );

            // The issue is that currently, for some unknown reason, foos are deleted on flush
        } );

        doInHibernate( this::sessionFactory, s -> {
            Bar bar = s.get( Bar.class, myBar.id );
            Assert.assertFalse( bar.foos.isEmpty() );
        } );
    }
