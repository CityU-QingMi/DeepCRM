    @BeforeClass
    public static void beforeClass() throws Exception {
      final Path src = FileSystems.getDefault().getPath(TARGET_TEST_CLASSES, CONFIG);
      String content = new String(Files.readAllBytes(src), UTF_8);
      final Calendar cal = Calendar.getInstance();
      cal.add(Calendar.SECOND, CRON_DELAY);
      remainingTime = cal.getTimeInMillis() - System.currentTimeMillis();
      cronExpression =  String.format("%d %d %d * * ?",
          cal.get(Calendar.SECOND),
          cal.get(Calendar.MINUTE),
          cal.get(Calendar.HOUR_OF_DAY));
      content = content.replace("@CRON_EXPR@", cronExpression);
      Files.write(FileSystems.getDefault()
            .getPath(TARGET_TEST_CLASSES, CONFIG_TARGET), content.getBytes(UTF_8));
      StatusLogger.getLogger().debug("Cron expression will be " + cronExpression + " in " + remainingTime + "ms");
    }
