    public void testWithRequired() throws Exception {
        List list = new ArrayList();
        list.add("Item One");
        list.add("Item Two");

        TestAction testaction = (TestAction) action;
        testaction.setCollection(list);

        InputTransferSelectTag tag = new InputTransferSelectTag();
        tag.setPageContext(pageContext);

        tag.setName("collection");
        tag.setList("collection");

        tag.doStartTag();
        tag.doEndTag();

        //System.out.println(writer.toString());
        verify(InputTransferSelectTagTest.class.getResource("inputtransferselect-1.txt"));
    }
