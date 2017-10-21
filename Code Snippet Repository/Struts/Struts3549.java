    public void testGetRenderRequestAndResponseInEventPhase() {
        context.put(REQUEST, renderRequest);
        context.put(RESPONSE, renderResponse);
        context.put(PHASE, PortletPhase.ACTION_PHASE);
        try {
            getRenderRequest();
            fail("Should throw IllegalStateException!");
        }
        catch(IllegalStateException e) {
            assertTrue(true);
        }
        try {
            getRenderResponse();
            fail("Should throw IllegalStateException!");
        }
        catch(IllegalStateException e) {
            assertTrue(true);
        }
    }
