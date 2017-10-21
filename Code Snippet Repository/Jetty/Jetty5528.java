    @Test
    public void testPut_Replace()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";
        Object ret;

        ret = mm.put(key,"gzip");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");
        Assert.assertNull("Should not have replaced anything", ret);
        Object orig = mm.get(key);

        // Now replace it
        ret = mm.put(key,"jar");
        assertMapSize(mm,1);
        assertValues(mm,key,"jar");
        Assert.assertEquals("Should have replaced original", orig, ret);
    }
