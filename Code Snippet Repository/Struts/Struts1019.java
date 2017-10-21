    public void testFieldErrors() {
        assertEquals(false, as.hasFieldErrors());
        assertEquals(0, as.getFieldErrors().size());
        as.addFieldError("username", "Admin is not allowed as username");
        List<String> errors = as.getFieldErrors().get("username");
        assertEquals(1, errors.size());
        assertEquals("Admin is not allowed as username", errors.get(0));

        assertEquals(true, as.hasFieldErrors());
        assertEquals(true, as.hasErrors());

        as.clearErrorsAndMessages();
        assertEquals(false, as.hasFieldErrors());
        assertEquals(false, as.hasErrors());
    }
