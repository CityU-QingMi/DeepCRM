    @Before
    public void before() {
        listAll = context.getListAppender("ListAll").clear();
        listTrace = context.getListAppender("ListTrace").clear();
        listDebug = context.getListAppender("ListDebug").clear();
        listInfo = context.getListAppender("ListInfo").clear();
        listWarn = context.getListAppender("ListWarn").clear();
        listError = context.getListAppender("ListError").clear();
        listFatal = context.getListAppender("ListFatal").clear();
    }
