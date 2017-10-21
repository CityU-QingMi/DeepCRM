    @Parameters(name = "")
    public static List<Object[]> getCases()
    {
        List<Object[]> ret = new ArrayList<>();

        ret.add(new Object[]{ "http2",
                "Invalid Java version",
                new String[]{"java.version=0.0.0_0"}});

        ret.add(new Object[]{ "versioned-modules-too-new",
                "Module [http3] specifies jetty version [10.0] which is newer than this version of jetty [" + RebuildTestResources.JETTY_VERSION + "]",
                null});

        return ret;
    }
