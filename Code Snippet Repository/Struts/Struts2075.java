    public void testTestFalse() {
        // set up the stack
        Foo foo = new Foo();
        foo.setNum(2);
        stack.push(foo);

        // set up the test
        tag.setTest("num != 2");

        int result = 0;

        try {
            result = tag.doStartTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(TagSupport.SKIP_BODY, result);

        try {
            result = tag.doEndTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }
    }
