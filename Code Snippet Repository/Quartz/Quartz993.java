    private File createConfigFile() {
      try {
        File configFile = writeConfigFile(TempDirectoryUtil.getTempDirectory(this.getClass()), getServerManager()
            .getServerTcConfig().getTsaPort());
        System.out.println("Wrote temp config file at: " + configFile.getAbsolutePath());
        return configFile;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
