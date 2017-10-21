    @Test
    public void testFindInPackageFromVfsDirectoryURL() throws Exception {
        try (final URLClassLoader cl = ResolverUtilTest.compileAndCreateClassLoader("3")) {
            final ResolverUtil resolverUtil = new ResolverUtil();
            resolverUtil
                    .setClassLoader(new SingleURLClassLoader(new URL("vfs:/" + ResolverUtilTest.WORK_DIR + "/resolverutil3/customplugin3/"), cl));
            resolverUtil.findInPackage(new PluginTest(), "customplugin3");
            assertEquals("Class not found in packages", 1, resolverUtil.getClasses().size());
            assertEquals("Unexpected class resolved", cl.loadClass("customplugin3.FixedString3Layout"),
                    resolverUtil.getClasses().iterator().next());
        }
    }
