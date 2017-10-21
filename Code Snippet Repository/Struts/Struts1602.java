    public void testValidUrlWithRegex() throws Exception {
        URLValidator validator = new URLValidator();

        validator.setUrlRegex("^myapp:\\/\\/[a-z]*\\.com$");

        Pattern pattern = Pattern.compile(validator.getUrlRegex());

        assertTrue(pattern.matcher("myapp://test.com").matches());
        assertFalse(pattern.matcher("myap://test.com").matches());
    }
