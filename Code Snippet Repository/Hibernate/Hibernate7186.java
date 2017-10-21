    @Test
    public void test() {
        ParentEntity[] parent = new ParentEntity[3];

        doInHibernate( this::sessionFactory, s -> {
            parent[0] = s.get( ParentEntity.class, entityId );
        } );

        checkDirtyTracking( parent[0] );

        parent[0].address.country.name = "Paraguai";

        checkDirtyTracking( parent[0], "address.country" );

        doInHibernate( this::sessionFactory, s -> {
            parent[1] = (ParentEntity) s.merge( parent[0] );
            checkDirtyTracking( parent[0], "address.country" );
            checkDirtyTracking( parent[1], "address.country" );
        } );

        checkDirtyTracking( parent[0], "address.country" );
        checkDirtyTracking( parent[1] );

        parent[1].address.country.name = "Honduras";

        checkDirtyTracking( parent[1], "address.country" );

        doInHibernate( this::sessionFactory, s -> {
            s.saveOrUpdate( parent[1] );
            checkDirtyTracking( parent[1], "address.country" );
        } );

        doInHibernate( this::sessionFactory, s -> {
            parent[2] = s.get( ParentEntity.class, entityId );
            Assert.assertEquals( "Honduras", parent[2].address.country.name );
        } );
    }
