    static File compile(final String suffix) throws IOException {
        final File orig = new File("target/test-classes/customplugin/FixedStringLayout.java.source");
        final File workDir = new File(WORK_DIR, "resolverutil" + suffix);
        final File f = new File(workDir, "customplugin" + suffix + "/FixedString" + suffix + "Layout.java");
        final File parent = f.getParentFile();
        if (!parent.exists()) {
          assertTrue("Create customplugin" + suffix + " folder KO", f.getParentFile().mkdirs());
        }
  
        final String content = new String(Files.readAllBytes(orig.toPath()))
          .replaceAll("FixedString", "FixedString" + suffix)
          .replaceAll("customplugin", "customplugin" + suffix);
        Files.write(f.toPath(), content.getBytes());
  
        PluginManagerPackagesTest.compile(f);
        return workDir;
    }
