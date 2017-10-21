    private void assertDirOrder(ConfigSources sources, Path... expectedDirOrder) throws IOException
    {
        List<String> actualList = new ArrayList<>();
        for (ConfigSource source : sources)
        {
            if (source instanceof DirConfigSource)
            {
                actualList.add(((DirConfigSource)source).getDir().toString());
            }
        }
        List<String> expectedList = new ArrayList<>();
        for (Path path : expectedDirOrder)
        {
            expectedList.add(path.toRealPath().toString());
        }
        ConfigurationAssert.assertOrdered("ConfigSources.dir order",expectedList,actualList);
    }
