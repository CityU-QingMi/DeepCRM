    @Parameters(name = "")
    public static List<Object[]> data()
    {
        List<Object[]> ret = new ArrayList<Object[]>();

        ret.add(new Object[] { 0, "empty.txt", !EXPECT_COMPRESSED});
        ret.add(new Object[] { TINY, "file-tiny.txt", !EXPECT_COMPRESSED});
        ret.add(new Object[] { SMALL, "file-small.txt", EXPECT_COMPRESSED});
        ret.add(new Object[] { SMALL, "file-small.mp3", !EXPECT_COMPRESSED});
        ret.add(new Object[] { MEDIUM, "file-med.txt", EXPECT_COMPRESSED});
        ret.add(new Object[] { MEDIUM, "file-medium.mp3", !EXPECT_COMPRESSED});
        ret.add(new Object[] { LARGE, "file-large.txt", EXPECT_COMPRESSED});
        ret.add(new Object[] { LARGE, "file-large.mp3", !EXPECT_COMPRESSED});

        return ret;
    }
