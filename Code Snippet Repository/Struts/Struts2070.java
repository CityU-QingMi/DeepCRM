    public void testUnexpectedPop() throws Exception {

         // set the resource bundle
        tag.setName("testmessages");

        int result = 0;

        try {
            result = tag.doStartTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        stack.push("An new object on top of the stack");

        assertEquals(TagSupport.EVAL_BODY_INCLUDE, result);

        try {
            result = tag.doEndTag();
            fail();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        } catch (StrutsException e) {
            e.printStackTrace();
            // pass
        }

    }
