    public void testParse() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new ActionSupport() {
            public String getMyLocation() {
                return "ThisIsMyLocation";
            }
        });

        ActionInvocation mockActionInvocation = EasyMock.createNiceMock(ActionInvocation.class);
        mockActionInvocation.getStack();
        EasyMock.expectLastCall().andReturn(stack);
        EasyMock.replay(mockActionInvocation);

        InternalStrutsResultSupport result = new InternalStrutsResultSupport();
        result.setParse(true);
        result.setEncode(false);
        result.setLocation("/pages/myJsp.jsp?location=${myLocation}");

        result.execute(mockActionInvocation);

        assertNotNull(result.getInternalLocation());
        assertEquals("/pages/myJsp.jsp?location=ThisIsMyLocation", result.getInternalLocation());
        EasyMock.verify(mockActionInvocation);
    }
