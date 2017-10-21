    @Test
    public void testBadDoubleRef() throws Exception
    {
        // Create home
        Path home = testdir.getPathFile("home");
        FS.ensureEmpty(home);
        TestEnv.copyTestDir("dist-home",home);

        // Create common
        Path common = testdir.getPathFile("common");
        FS.ensureEmpty(common);

        // Create corp
        Path corp = testdir.getPathFile("corp");
        FS.ensureEmpty(corp);
        TestEnv.makeFile(corp,"start.ini",
        // standard property
                "jetty.http.port=9090",
                // INTENTIONAL BAD Reference (duplicate)
                "--include-jetty-dir=" + common.toString());

        // Populate common
        TestEnv.makeFile(common,"start.ini",
        // standard property
                "jetty.http.port=8080",
                // reference to corp
                "--include-jetty-dir=" + corp.toString());

        // Create base
        Path base = testdir.getPathFile("base");
        FS.ensureEmpty(base);
        TestEnv.makeFile(base,"start.ini", //
                "jetty.http.host=127.0.0.1",//
                "--include-jetty-dir=" + common.toString());

        try
        {
            runMain(base,home);
            Assert.fail("Should have thrown a UsageException");
        }
        catch (UsageException e)
        {
            Assert.assertThat("UsageException",e.getMessage(),containsString("Duplicate"));
        }
    }
