    public void testMultipleWithLists() throws Exception {
        TestAction testAction = (TestAction) action;
        Collection collection = new ArrayList(2);

        collection.add(1l);
        collection.add(300000000l);
        testAction.setCollection(collection);

        List selectList = new ArrayList();
        selectList.add(new LongObject(1l, "foo"));
        selectList.add(new LongObject(2l, "bar"));
        selectList.add(new LongObject(300000000l, "foobar"));
        testAction.setList2(selectList);

        SelectTag tag = new SelectTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("collection");
        tag.setList("list2");
        tag.setListKey("id");
        tag.setListValue("value");
        tag.setMultiple("true");
        tag.setOnmousedown("alert('onmousedown');");
        tag.setOnmousemove("alert('onmousemove');");
        tag.setOnmouseout("alert('onmouseout');");
        tag.setOnmouseover("alert('onmouseover');");
        tag.setOnmouseup("alert('onmouseup');");

        tag.doStartTag();
        tag.doEndTag();

        verify(SelectTag.class.getResource("Select-12.txt"));
    }
