    @Parameterized.Parameters(name = "")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        { "RandomAccessFileAppenderTest", false, ".xml" },
                        { "RandomAccessFileAppenderLocationTest", true, ".xml" },
                        { "RollingRandomAccessFileAppenderTest", false, ".xml" },
                        { "RollingRandomAccessFileAppenderLocationTest", true, ".xml" },
                        { "RollingRandomAccessFileAppenderLocationPropsTest", false, ".properties" }
                }
        );
    }
