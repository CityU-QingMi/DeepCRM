    public void testCheckBox() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("true");

        CheckboxTag tag = new CheckboxTag();
        Mock rdMock = new Mock(RequestDispatcher.class);
        rdMock.expect("include",C.args(C.isA(HttpServletRequest.class), C.isA(HttpServletResponse.class)));
        RequestDispatcher dispatcher = (RequestDispatcher) rdMock.proxy();
        request.setupGetRequestDispatcher(dispatcher);
        tag.setPageContext(pageContext);
        tag.setTemplate("/test/checkbox.jsp");
        tag.doStartTag();
        tag.doEndTag();
        rdMock.verify();
    }
