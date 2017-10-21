    @Override
    protected void setUp() throws Exception {
        super.setUp();

        sac = new StaticApplicationContext();

        //SpringObjectFactory objFactory = new SpringObjectFactory();
        //objFactory.setApplicationContext(sac);
        //ObjectFactory.setObjectFactory(objFactory);

        objectFactory = container.getInstance(ObjectFactory.class);
    }
