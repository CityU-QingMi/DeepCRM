    @Before
    public void prepareTestData() {
        EntityManager em = getOrCreateEntityManager();
        em.getTransaction().begin();

        Thing thing = new Thing();
        thing.setId( "thing1" );
        thing.setName( "A Thing" );
        em.persist( thing );

        thing = new Thing();
        thing.setId( "thing2" );
        thing.setName( "Another Thing" );
        em.persist( thing );

        ThingWithQuantity thingWithQuantity = new ThingWithQuantity();
        thingWithQuantity.setId( "thingWithQuantity3" );
        thingWithQuantity.setName( "3 Things" );
        thingWithQuantity.setQuantity( 3 );
        em.persist( thingWithQuantity );

        em.getTransaction().commit();
        em.close();
    }
