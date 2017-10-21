    public void testFieldValidators() {
        beginAt("/validation/showFieldValidatorsExamples.action");

        setTextField("integerValidatorField", "nonint");
        setTextField("dateValidatorField", "nondate");
        setTextField("emailValidatorField", "!@@#%");
        setTextField("urlValidatorField", "!@@#%");
        setTextField("stringLengthValidatorField", "a");
        setTextField("regexValidatorField", "abc");
        setTextField("fieldExpressionValidatorField", "abc");

        submit();

        assertTextPresent("Invalid field value for field \"dateValidatorField\"");
        assertTextPresent("Invalid field value for field \"integerValidatorField\"");
        assertTextPresent("required and must be string");
        assertTextPresent("must be a valid email if supplied");
        assertTextPresent("must be a valid url if supplied ");
        assertTextPresent("must be a String of a specific greater than 1 less than 5 if specified ");
        assertTextPresent("regexValidatorField must match a regexp (.*\\.txt) if specified ");
        assertTextPresent("must be the same as the Required Validator Field if specified ");
    }
