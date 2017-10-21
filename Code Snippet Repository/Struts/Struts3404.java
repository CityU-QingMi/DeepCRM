    @Test
    public void shouldPortletContextBeAvailable() throws Exception {
        // given
        assertNull(ActionContext.getContext().get(StrutsStatics.STRUTS_PORTLET_CONTEXT));

        // when
        String output = executeAction("/test/testAction.action");
        assertEquals("Hello", output);

        // then
        Object portletContext = ActionContext.getContext().get(StrutsStatics.STRUTS_PORTLET_CONTEXT);
        assertNotNull(portletContext);
        assertTrue(portletContext instanceof PortletContext);
    }
