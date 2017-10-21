    public void testConditionalParseCollection() throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(new ActionSupport() {
            public List<String> getList() {
                return new ArrayList<String>(){{
                    add("val 1");
                    add("val 2");
                }};
            }
        });

        ActionInvocation mockActionInvocation = EasyMock.createNiceMock(ActionInvocation.class);
        mockActionInvocation.getStack();
        EasyMock.expectLastCall().andReturn(stack);
        EasyMock.replay(mockActionInvocation);

        InternalStrutsResultSupport result = new InternalStrutsResultSupport();
        result.setParse(true);
        result.setEncode(true);

        Collection<String> collection = result.conditionalParseCollection("${list}", mockActionInvocation, true);

        assertNotNull(collection);
        assertEquals(2, collection.size());
        assertTrue(collection.contains("val+1"));
        assertTrue(collection.contains("val+2"));
        EasyMock.verify(mockActionInvocation);
    }
