    public void testGetThemeFromContextNonString() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        Map context = Collections.singletonMap("theme", 12);
        ActionContext.getContext().put("attr", context);

        TextField txtFld = new TextField(stack, req, res);
        assertEquals("12", txtFld.getTheme());
    }
