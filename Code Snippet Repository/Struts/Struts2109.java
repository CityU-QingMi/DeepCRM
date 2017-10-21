    protected void setUp() throws Exception {
        super.setUp();

        // create the needed objects
        tag = new IteratorTag();

        MockBodyContent mockBodyContent = new TestMockBodyContent();
        mockBodyContent.setupGetEnclosingWriter(new MockJspWriter());
        tag.setBodyContent(mockBodyContent);

        // associate the tag with the mock page request
        tag.setPageContext(pageContext);
    }
