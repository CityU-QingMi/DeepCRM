    public static Matcher<File> hasNumberOfFiles(final Matcher<Integer> matcher) {
        return new FeatureMatcher<File, Integer>(matcher, "directory with number of files",
            "directory with number of files") {
            @Override
            protected Integer featureValueOf(final File actual) {
                final File[] files = actual.listFiles();
                return files == null ? 0 : files.length;
            }
        };
    }
