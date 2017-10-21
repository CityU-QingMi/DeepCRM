    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.container = EasyMock.createNiceMock(Container.class);
        EasyMock.expect(container.getInstance(String.class, ConventionConstants.CONVENTION_CONVENTIONS_SERVICE)).andReturn("convention").anyTimes();
        EasyMock.expect(container.getInstance(ConventionsService.class, "convention")).andAnswer(new IAnswer<ConventionsService>() {
            public ConventionsService answer() throws Throwable {
                return DefaultResultMapBuilderTest.this.conventionsService;
            }
        }).anyTimes();
        EasyMock.replay(this.container);
    }
