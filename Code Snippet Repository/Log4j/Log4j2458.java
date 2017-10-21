    @Test
    public void testNumberConvertersInvalidError() {
        parseInvalidValue("-Byte", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-byte", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-Short", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-short", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-Integer", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-int", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-Long", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-long", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-Float", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-float", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-Double", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-double", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
        parseInvalidValue("-BigDecimal", "aa", ": java.lang.NumberFormatException");
        parseInvalidValue("-BigInteger", "aa", ": java.lang.NumberFormatException: For input string: \"aa\"");
    }
