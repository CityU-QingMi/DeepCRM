    public void testGetActionRequestAndResponseInRenderPhase() {
        context.put(REQUEST, actionRequest);
        context.put(RESPONSE, actionResponse);
        context.put(PHASE, PortletPhase.RENDER_PHASE);
        try {
            getActionRequest();
            fail("Should throw IllegalStateException!");
        }
        catch(IllegalStateException e) {
            assertTrue(true);
        }
        try {
            getActionResponse();
            fail("Should throw IllegalStateException!");
        }
        catch(IllegalStateException e) {
            assertTrue(true);
        }
    }
