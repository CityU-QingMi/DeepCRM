    @Parameterized.Parameters(name = "")
    public static Collection<Object[]> data() throws IOException {
        return Arrays.asList(new Object[][] { //
              // @formatter:off
             {"rwxrwxrwx", true, 2},
             {"rw-r--r--", false, 3},
             {"rw-------", true, 4},
             {"rw-rw----", false, 5},
              });
              // @formatter:on
    }
