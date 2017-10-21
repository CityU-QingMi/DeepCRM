    public void testValidDinersClubCard() throws Exception {
        // given
        action.setDinersClub("30569309025904");
        validator.setFieldName("dinersClub");

        // when
        validator.validate(action);

        // then
        assertFalse(context.hasFieldErrors());
    }
