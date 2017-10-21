    public void testSimpleInteger() throws Exception {
        TestAction testAction = (TestAction) action;

        IdName hello = new IdName(new Integer(1), "hello");
        IdName world = new IdName(new Integer(2), "world");
        List list2 = new ArrayList();
        list2.add(hello);
        list2.add(world);
        testAction.setList2(list2);

        testAction.setFooInt(new Integer(1));

        SelectTag tag = new SelectTag();
        tag.setPageContext(pageContext);
        tag.setEmptyOption("true");
        tag.setLabel("mylabel");
        tag.setName("fooInt");
        tag.setList("list2");
        tag.setListKey("id");
        tag.setListValue("name");

        // header stuff
        tag.setHeaderKey("headerKey");
        tag.setHeaderValue("headerValue");

        // empty option
        tag.setEmptyOption("true");

        tag.doStartTag();
        tag.doEndTag();

        verify(SelectTag.class.getResource("Select-11.txt"));
    }
