    public void testEscape() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        UIBean bean = new UIBean(stack, req, res) {
            protected String getDefaultTemplate() {
                return null;
            }
        };

        assertEquals(bean.escape("hello[world"), "hello_world");
        assertEquals(bean.escape("hello.world"), "hello_world");
        assertEquals(bean.escape("hello]world"), "hello_world");
        assertEquals(bean.escape("hello!world"), "hello!world");
        assertEquals(bean.escape("hello!@#$%^&*()world"), "hello!@#$%^&*()world");
    }
