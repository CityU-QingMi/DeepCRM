    public static Matcher<Path> fileExists()
    {
        return new BaseMatcher<Path>()
        {
            @Override
            public boolean matches(Object item)
            {
                final Path path = (Path)item;
                return Files.exists(path) && Files.isRegularFile(path);
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText("File should exist");
            }
            
            @Override
            public void describeMismatch(Object item, Description description)
            {
                description.appendText("File did not exist ").appendValue(item);
            }
        };
    }
