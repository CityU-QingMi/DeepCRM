    public void testNullList() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setList2(null);

        SelectTag tag = new SelectTag();
        tag.setName("collection");
        tag.setList("list2");
        tag.setLabel("tmjee_name");

        tag.setPageContext(pageContext);
        try {
            tag.doStartTag();
            tag.doEndTag();
            fail("exception should have been thrown value of select tag is null");
        }
        catch(Exception e) {
            assertTrue(true);
        }
    }
