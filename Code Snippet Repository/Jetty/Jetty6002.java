    @Test
    public void testLarge()
    {
        ClasspathPattern pattern = new ClasspathPattern();
        for (int i = 0; i < 500; i++)
        {
            Assert.assertTrue(pattern.add("n" + i + "." + Integer.toHexString(100 + i) + ".Name"));
        }

        for (int i = 0; i < 500; i++)
        {
            Assert.assertTrue(pattern.match("n" + i + "." + Integer.toHexString(100 + i) + ".Name"));
        }
    }
