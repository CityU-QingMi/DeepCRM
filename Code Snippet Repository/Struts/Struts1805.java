    public void testAllowedActionNames() throws Exception {
        DefaultActionMapper mapper = new DefaultActionMapper();

        String actionName = "action";
        assertEquals(actionName, mapper.cleanupActionName(actionName));

        actionName = "${action}";
        assertEquals(mapper.defaultActionName, mapper.cleanupActionName(actionName));

        actionName = "${${%{action}}}";
        assertEquals(mapper.defaultActionName, mapper.cleanupActionName(actionName));

        actionName = "${#foo='action',#foo}";
        assertEquals(mapper.defaultActionName, mapper.cleanupActionName(actionName));

        actionName = "test-action";
        assertEquals("test-action", mapper.cleanupActionName(actionName));

        actionName = "test_action";
        assertEquals("test_action", mapper.cleanupActionName(actionName));

        actionName = "test!bar.action";
        assertEquals("test!bar.action", mapper.cleanupActionName(actionName));
    }
