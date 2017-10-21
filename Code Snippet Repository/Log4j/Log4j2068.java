    @Test
    public void testFindInPackageFromDirectoryPath() throws Exception {
        try (final URLClassLoader cl = compileAndCreateClassLoader("1")) {
            final ResolverUtil resolverUtil = new ResolverUtil();
            resolverUtil.setClassLoader(cl);
            resolverUtil.findInPackage(new PluginTest(), "customplugin1");
            assertEquals("Class not found in packages", 1, resolverUtil.getClasses().size());
            assertEquals("Unexpected class resolved", cl.loadClass("customplugin1.FixedString1Layout"),
                    resolverUtil.getClasses().iterator().next());
        }
    }
