    protected void tearDown() throws Exception {
        super.tearDown();
        pageContext.verify();
        request.verify();
        action = null;
        context = null;
        session = null;
        stack = null;
        writer = null;
        request = null;
        pageContext = null;
        response = null;
        servletContext = null;
        mockContainer = null;
    }
