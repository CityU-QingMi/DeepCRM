    private void validateCreditCardValidator(CreditCardValidator validator) {
        assertEquals("foo", validator.getFieldName());
        assertEquals(CreditCardValidator.CREDIT_CARD_PATTERN, validator.getRegex());
        assertEquals("Foo isn't a valid credit card!", validator.getDefaultMessage());
        assertEquals("creditCard.key", validator.getMessageKey());
        assertTrue(Arrays.equals(new String[]{"one", "two", "three"}, validator.getMessageParameters()));
        assertEquals(true, validator.isShortCircuit());
        assertEquals(false, validator.isCaseSensitive());
        assertEquals(true, validator.isTrimed());
    }
