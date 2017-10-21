    @Test
    public void testFindInPackageFromVfsJarURL() throws Exception {
        try (final URLClassLoader cl = ResolverUtilTest.compileJarAndCreateClassLoader("4")) {
            final ResolverUtil resolverUtil = new ResolverUtil();
            resolverUtil.setClassLoader(new SingleURLClassLoader(
                    new URL("vfs:/" + ResolverUtilTest.WORK_DIR + "/resolverutil4/customplugin4.jar/customplugin4/"), cl));
            resolverUtil.findInPackage(new PluginTest(), "customplugin4");
            assertEquals("Class not found in packages", 1, resolverUtil.getClasses().size());
            assertEquals("Unexpected class resolved", cl.loadClass("customplugin4.FixedString4Layout"),
                    resolverUtil.getClasses().iterator().next());
        }
    }
