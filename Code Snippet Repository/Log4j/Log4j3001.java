    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {DebugTag.class, Level.DEBUG},
            {ErrorTag.class, Level.ERROR},
            {FatalTag.class, Level.FATAL},
            {InfoTag.class, Level.INFO},
            {TraceTag.class, Level.TRACE},
            {WarnTag.class, Level.WARN}
        });
    }
