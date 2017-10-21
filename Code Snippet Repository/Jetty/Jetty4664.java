    public static Matcher<Path> pathExists()
    {
        return new BaseMatcher<Path>()
        {
            @Override
            public boolean matches(Object item)
            {
                final Path path = (Path)item;
                return Files.exists(path);
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText("Path should exist");
            }
            
            @Override
            public void describeMismatch(Object item, Description description)
            {
                description.appendText("Path did not exist ").appendValue(item);
            }
        };
    }
