    @Parameterized.Parameters(name = "")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { //
                // @formatter:off
                {"log4j-rolling-with-custom-delete-unconditional1.xml", "target/rolling-unconditional-delete1/test"}, //
                {"log4j-rolling-with-custom-delete-unconditional2.xml", "target/rolling-unconditional-delete2/test"}, //
                {"log4j-rolling-with-custom-delete-unconditional3.xml", "target/rolling-unconditional-delete3/test"}, //
                // @formatter:on
        });
    }
