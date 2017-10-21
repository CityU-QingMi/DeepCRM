    @Test
    public void shouldAdditionalContextParamsBeAvailable() throws Exception {
        // given
        String key = "my-param";
        assertNull(ActionContext.getContext().get(key));

        // when
        String output = executeAction("/test/testAction.action");
        assertEquals("Hello", output);

        // then
        assertNotNull(ActionContext.getContext().get(key));
    }
