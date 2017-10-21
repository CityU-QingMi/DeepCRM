    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            Parent parent = s.get( Parent.class, 1L );
           
            Child child = new Child();
            child.setId( 1L );
            s.save( child );
            parent.addChild( child );

            // We need to leave at least one attribute unfetchd
            //parent.getNames().size();
            s.save( parent );
        } );

        doInHibernate( this::sessionFactory, s -> {
            Parent application = s.get( Parent.class, 1L );
            Assert.assertEquals( "Loaded Children collection has unexpected size", 2, application.getChildren().size() );
        } );
    }
