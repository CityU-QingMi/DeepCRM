    @Parameterized.Parameters(name = "")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { //
                // @formatter:off
               {"log4j-rolling-gz-lazy.xml", ".gz", true},
               {"log4j-rolling-gz.xml", ".gz", false},
               {"log4j-rolling-zip-lazy.xml", ".zip", true},
               {"log4j-rolling-zip.xml", ".zip", false},
                // Apache Commons Compress
               {"log4j-rolling-bzip2-lazy.xml", ".bz2", true},
               {"log4j-rolling-bzip2.xml", ".bz2", false},
               {"log4j-rolling-deflate-lazy.xml", ".deflate", true},
               {"log4j-rolling-deflate.xml", ".deflate", false},
               {"log4j-rolling-pack200-lazy.xml", ".pack200", true},
               {"log4j-rolling-pack200.xml", ".pack200", false},
               {"log4j-rolling-xz-lazy.xml", ".xz", true},
               {"log4j-rolling-xz.xml", ".xz", false},
                });
                // @formatter:on
    }
