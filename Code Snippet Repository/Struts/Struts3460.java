    public void testFieldsWithMultipleProfiles() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "fieldsWidthProfiles13", null, null);
        FieldsWithProfiles action = (FieldsWithProfiles) baseActionProxy.getAction();
        baseActionProxy.execute();

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertNotNull(fieldErrors);
        assertEquals(2, fieldErrors.size());
        assertNotNull(fieldErrors.get("firstName"));
        assertNotNull(fieldErrors.get("lastName"));
    }
