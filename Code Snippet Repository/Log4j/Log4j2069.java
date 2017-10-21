    @Test
    public void testFindInPackageFromJarPath() throws Exception {
        try (final URLClassLoader cl = compileJarAndCreateClassLoader("2")) {
            final ResolverUtil resolverUtil = new ResolverUtil();
            resolverUtil.setClassLoader(cl);
            resolverUtil.findInPackage(new PluginTest(), "customplugin2");
            assertEquals("Class not found in packages", 1, resolverUtil.getClasses().size());
            assertEquals("Unexpected class resolved", cl.loadClass("customplugin2.FixedString2Layout"),
                    resolverUtil.getClasses().iterator().next());
        }
    }
