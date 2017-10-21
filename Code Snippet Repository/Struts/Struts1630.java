    public void testInvalidCollectionOfCardNumbers() throws Exception {
        // given
        action.setDinerClubs(Arrays.asList("75736151433"));
        validator.setFieldName("dinerClubs");
        validator.setDefaultMessage("It is not a valid Diner Club card number: ${currentValue}");

        // when
        validator.validate(action);

        // then
        assertTrue(context.hasFieldErrors());
        assertEquals(1, context.getFieldErrors().size());
        assertEquals("It is not a valid Diner Club card number: 75736151433", context.getFieldErrors().get("dinerClubs").get(0));
    }
