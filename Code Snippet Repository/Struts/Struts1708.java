    public void testGetThemeFromForm() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        Form form = new Form(stack, req, res);
        form.setTheme("foo");

        TextField txtFld = new TextField(stack, req, res);
        assertEquals("foo", txtFld.getTheme());
    }
