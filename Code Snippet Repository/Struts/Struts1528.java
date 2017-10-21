    @Override
    protected void setUp() throws Exception {
        actionValidatorManager = new DefaultActionValidatorManager();
        super.setUp();
        mockValidatorFileParser = new Mock(ValidatorFileParser.class);
        actionValidatorManager.setValidatorFileParser((ValidatorFileParser)mockValidatorFileParser.proxy());

        mockValidatorFactory = new Mock(ValidatorFactory.class);
        actionValidatorManager.setValidatorFactory((ValidatorFactory)mockValidatorFactory.proxy());

        stubValueStack = new StubValueStack();
        ActionContext.setContext(new ActionContext(new HashMap<String, Object>()));
        ActionContext.getContext().setValueStack(stubValueStack);

        DefaultFileManagerFactory factory = new DefaultFileManagerFactory();
        factory.setContainer(container);
        factory.setFileManager(new DefaultFileManager());
        actionValidatorManager.setFileManagerFactory(factory);
    }
