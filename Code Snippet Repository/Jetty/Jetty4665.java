    public static Matcher<Path> notPathExists()
    {
        return new BaseMatcher<Path>()
        {
            @Override
            public boolean matches(Object item)
            {
                final Path path = (Path)item;
                return !Files.exists(path);
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText("Path should not exist");
            }
            
            @Override
            public void describeMismatch(Object item, Description description)
            {
                description.appendText("Path exists ").appendValue(item);
            }
        };
    }
