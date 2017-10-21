    public void testLongRunningValidations() throws Exception {
        URLValidator validator = new URLValidator();

        Pattern pattern = Pattern.compile(validator.getUrlRegex(), Pattern.CASE_INSENSITIVE);

        long time = System.currentTimeMillis();
        assertFalse(pattern.matcher("ftp://aaaaaaaaaaaaaaaaaaaaaaaa|").matches());
        assertTrue("Validation did not complete in half a second", System.currentTimeMillis() - time < 500);

        time = System.currentTimeMillis();
        assertFalse(pattern.matcher("ftp://bbbbbbbbbbbbbbbbbbbbbbbb}").matches());
        assertTrue("Validation did not complete in half a second", System.currentTimeMillis() - time < 500);

        time = System.currentTimeMillis();
        assertFalse(pattern.matcher("ftp://cccccccccccccccccccccccc{").matches());
        assertTrue("Validation did not complete in half a second", System.currentTimeMillis() - time < 500);
    }
