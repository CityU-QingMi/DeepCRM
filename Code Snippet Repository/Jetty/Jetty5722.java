    @Test
    public void testIsDebugEnabled() {
        JavaUtilLog log = new JavaUtilLog("test.legacy");

        setJulLevel("test.legacy",Level.ALL);
        Assert.assertThat("log.level(all).isDebugEnabled", log.isDebugEnabled(), is(true));

        setJulLevel("test.legacy",Level.FINEST);
        Assert.assertThat("log.level(finest).isDebugEnabled", log.isDebugEnabled(), is(true));

        setJulLevel("test.legacy",Level.FINER);
        Assert.assertThat("log.level(finer).isDebugEnabled", log.isDebugEnabled(), is(true));

        setJulLevel("test.legacy",Level.FINE);
        Assert.assertThat("log.level(fine).isDebugEnabled", log.isDebugEnabled(), is(true));

        setJulLevel("test.legacy",Level.INFO);
        Assert.assertThat("log.level(info).isDebugEnabled", log.isDebugEnabled(), is(false));

        setJulLevel("test.legacy",Level.WARNING);
        Assert.assertThat("log.level(warn).isDebugEnabled", log.isDebugEnabled(), is(false));

        log.setDebugEnabled(true);
        Assert.assertThat("log.isDebugEnabled", log.isDebugEnabled(), is(true));

        log.setDebugEnabled(false);
        Assert.assertThat("log.isDebugEnabled", log.isDebugEnabled(), is(false));
    }
