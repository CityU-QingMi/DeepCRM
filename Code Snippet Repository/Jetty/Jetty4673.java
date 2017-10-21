    private void assertParse(String verStr, int legacyMajor, int major, int revision, int update)
    {
        Version ver = new Version(verStr);
        assertThat("Version [" + verStr + "].legacyMajor", ver.getLegacyMajor(), is(legacyMajor));
        assertThat("Version [" + verStr + "].major", ver.getMajor(), is(major));
        assertThat("Version [" + verStr + "].revision", ver.getRevision(), is(revision));
        assertThat("Version [" + verStr + "].update", ver.getUpdate(), is(update));
        
        assertThat("Version [" + verStr + "].toString", ver.toString(), is(verStr));
    }
