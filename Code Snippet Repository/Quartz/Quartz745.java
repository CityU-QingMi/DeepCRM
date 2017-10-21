    private void assertNonNegativeInteger(String s) {
        assertNotNull(s);
        boolean parsed = false;
        int intVal = -1;
        try {
            intVal = Integer.parseInt(s);
            parsed = true;
        } catch (NumberFormatException e) {}

        assertTrue(parsed);
        assertTrue(intVal >= 0);
    }
