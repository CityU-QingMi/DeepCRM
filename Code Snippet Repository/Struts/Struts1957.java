    public void testParseAndEncode() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new ActionSupport() {
            public String getMyLocation() {
                return "/myPage?param=value&param1=value1";
            }
        });

        ActionInvocation mockActionInvocation = EasyMock.createNiceMock(ActionInvocation.class);
        mockActionInvocation.getStack();
        EasyMock.expectLastCall().andReturn(stack);
        EasyMock.replay(mockActionInvocation);

        InternalStrutsResultSupport result = new InternalStrutsResultSupport();
        result.setParse(true);
        result.setEncode(true);
        result.setLocation("/pages/myJsp.jsp?location=${myLocation}");

        result.execute(mockActionInvocation);

        assertNotNull(result.getInternalLocation());
        assertEquals("/pages/myJsp.jsp?location=%2FmyPage%3Fparam%3Dvalue%26param1%3Dvalue1", result.getInternalLocation());
        EasyMock.verify(mockActionInvocation);
    }
