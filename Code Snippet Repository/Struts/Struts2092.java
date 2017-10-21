    public void testIteratingWithIdSpecifiedAndNullElementOnCollection() throws Exception {
        List list = new ArrayList();
        list.add("one");
        list.add(null);
        list.add("three");

        Foo foo = new Foo();
        foo.setList(list);

        stack.push(foo);

        tag.setValue("list");
        tag.setVar("myId");

        // one
        int result = tag.doStartTag();
        assertEquals(result, TagSupport.EVAL_BODY_INCLUDE);
        assertEquals(stack.peek(), "one");
        assertEquals(stack.getContext().get("myId"), "one");


        tag.doInitBody();

        // two
        result = tag.doAfterBody();
        assertEquals(result, TagSupport.EVAL_BODY_AGAIN);
        assertNull(stack.peek());
        assertNull(stack.getContext().get("myId"));


        // three
        result = tag.doAfterBody();
        assertEquals(result, TagSupport.EVAL_BODY_AGAIN);
        assertEquals(stack.peek(), "three");
        assertEquals(stack.getContext().get("myId"), "three");

        result = tag.doAfterBody();
        assertEquals(result, TagSupport.SKIP_BODY);

        result = tag.doEndTag();
        assertEquals(result, TagSupport.EVAL_PAGE);
    }
