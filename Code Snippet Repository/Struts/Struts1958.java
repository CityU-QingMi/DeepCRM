    public void testNoParseAndEncode() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new ActionSupport() {
            public String getMyLocation() {
                return "myLocation.jsp";
            }
        });

        ActionInvocation mockActionInvocation = EasyMock.createNiceMock(ActionInvocation.class);
        EasyMock.replay(mockActionInvocation);

        InternalStrutsResultSupport result = new InternalStrutsResultSupport();
        result.setParse(false);
        result.setEncode(false); // don't really need this, as encode is only valid when parse is true.
        result.setLocation("/pages/myJsp.jsp?location=${myLocation}");

        result.execute(mockActionInvocation);

        assertNotNull(result.getInternalLocation());
        assertEquals("/pages/myJsp.jsp?location=${myLocation}", result.getInternalLocation());
        EasyMock.verify(mockActionInvocation);
    }
