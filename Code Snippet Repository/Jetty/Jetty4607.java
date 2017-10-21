    public static void assertPathList(BaseHome hb, String message, List<String> expected, PathFinder finder)
    {
        List<String> actual = new ArrayList<>();
        for (Path path : finder.getHits())
        {
            actual.add(hb.toShortForm(path.toFile()));
        }

        if (actual.size() != expected.size())
        {
            System.out.printf("Actual Path(s): %,d hits%n",actual.size());
            for (String path : actual)
            {
                System.out.printf(" %s%n",path);
            }
            System.out.printf("Expected Path(s): %,d entries%n",expected.size());
            for (String path : expected)
            {
                System.out.printf(" %s%n",path);
            }
        }
        Assert.assertThat(message + ": " + Utils.join(actual,", "),actual,containsInAnyOrder(expected.toArray()));
    }
