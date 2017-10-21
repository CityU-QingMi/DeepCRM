    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {PageContext.APPLICATION_SCOPE, "application"},
            {PageContext.PAGE_SCOPE, "page"},
            {PageContext.REQUEST_SCOPE, "request"},
            {PageContext.SESSION_SCOPE, "session"},
            {PageContext.PAGE_SCOPE, "insert random garbage here"}
        });
    }
