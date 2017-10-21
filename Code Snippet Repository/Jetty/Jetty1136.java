    @Test
    public void testGenerateParseSettings() throws Exception
    {
        Map<Integer, Integer> settings1 = new HashMap<>();
        int key1 = 13;
        Integer value1 = 17;
        settings1.put(key1, value1);
        int key2 = 19;
        Integer value2 = 23;
        settings1.put(key2, value2);
        List<SettingsFrame> frames = testGenerateParse(settings1);
        Assert.assertEquals(1, frames.size());
        SettingsFrame frame = frames.get(0);
        Map<Integer, Integer> settings2 = frame.getSettings();
        Assert.assertEquals(2, settings2.size());
        Assert.assertEquals(value1, settings2.get(key1));
        Assert.assertEquals(value2, settings2.get(key2));
    }
