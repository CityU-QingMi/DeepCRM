    @Before
    public void setUp() {
        given(provider.getConnection()).willReturn(connection);
        given(connection.createObject()).willAnswer(new Answer<DefaultNoSqlObject>() {
            @Override
            public DefaultNoSqlObject answer(final InvocationOnMock invocation) throws Throwable {
                return new DefaultNoSqlObject();
            }
        });
        given(connection.createList(anyInt())).willAnswer(new Answer<DefaultNoSqlObject[]>() {
            @Override
            public DefaultNoSqlObject[] answer(final InvocationOnMock invocation) throws Throwable {
                return new DefaultNoSqlObject[invocation.<Integer>getArgument(0)];
            }
        });
    }
