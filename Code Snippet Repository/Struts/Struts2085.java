    protected void setUp() throws Exception {
        super.setUp();
        request.setupGetRequestDispatcher(new MockRequestDispatcher());
        tag = new IncludeTag();

        mockRequestDispatcher = (RequestDispatcher) createMock(RequestDispatcher.class);

        request.setupGetRequestDispatcher(mockRequestDispatcher);
        tag.setPageContext(pageContext);
        tag.setPageContext(pageContext);
    }
