    private void assertConfig(ExtensionConfig cfg, String expectedName, Map<String, String> expectedParams)
    {
        String prefix = "ExtensionConfig";
        Assert.assertThat(prefix + ".Name",cfg.getName(),is(expectedName));

        prefix += ".getParameters()";
        Map<String, String> actualParams = cfg.getParameters();
        Assert.assertThat(prefix,actualParams,notNullValue());
        Assert.assertThat(prefix + ".size",actualParams.size(),is(expectedParams.size()));

        for (String expectedKey : expectedParams.keySet())
        {
            Assert.assertThat(prefix + ".containsKey(" + expectedKey + ")",actualParams.containsKey(expectedKey),is(true));

            String expectedValue = expectedParams.get(expectedKey);
            String actualValue = actualParams.get(expectedKey);

            Assert.assertThat(prefix + ".containsKey(" + expectedKey + ")",actualValue,is(expectedValue));
        }
    }
