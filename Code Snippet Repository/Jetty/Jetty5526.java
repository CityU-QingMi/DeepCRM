    private void assertValues(MultiMap<String> mm, String key, Object... expectedValues)
    {
        List<String> values = mm.getValues(key);

        String prefix = "MultiMap.getValues(" + key + ")";

        Assert.assertEquals(prefix + ".size",expectedValues.length,values.size());
        int len = expectedValues.length;
        for (int i = 0; i < len; i++)
        {
            if(expectedValues[i] == null) {
                Assert.assertThat(prefix + "[" + i + "]",values.get(i),nullValue());
            } else {
                Assert.assertEquals(prefix + "[" + i + "]",expectedValues[i],values.get(i));
            }
        }
    }
