    private File writeConfigFile(File tempDirectory, int dsoPort) throws IOException {
      InputStream in = null;
      FileOutputStream out = null;

      try {
        in = getClass().getClassLoader().getResourceAsStream("org/terracotta/quartz/tests/container/quartz.properties");
        File rv = new File(tempDirectory, "basic-quartz.properties");
        out = new FileOutputStream(rv);
        String template = IOUtils.toString(in);
        String config = template.replace("__PORT__", String.valueOf(dsoPort));
        out.write(config.getBytes());
        return rv;
      } finally {
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
      }
    }
