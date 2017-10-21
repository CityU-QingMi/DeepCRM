    private void assertIdOrder(ConfigSources sources, String... expectedOrder)
    {
        List<String> actualList = new ArrayList<>();
        for (ConfigSource source : sources)
        {
            actualList.add(source.getId());
        }
        List<String> expectedList = Arrays.asList(expectedOrder);
        ConfigurationAssert.assertOrdered("ConfigSources.id order",expectedList,actualList);
    }
