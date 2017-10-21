    @Test
    public void testAppender() throws Exception {
      final List<String> messages = new ArrayList<>();
        for (int i=0; i < 1000; ++i) {
          final String message = "This is test message number " + i;
          messages.add(message);
            logger.debug(message);
            if (i % 100 == 0) {
              Thread.sleep(500);
            }
        }
        if (!loggerContextRule.getLoggerContext().stop(30, TimeUnit.SECONDS)) {
            System.err.println("Could not stop cleanly " + loggerContextRule + " for " + this);
        }
        final File dir = new File(DIR);
        assertTrue("Directory not created", dir.exists());
        final File[] files = dir.listFiles();
        assertNotNull(files);
        for (final File file : files) {
          final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (FileInputStream fis = new FileInputStream(file)) {
              try {
                    IOUtils.copy(fis, baos);
                } catch (final Exception ex) {
                    ex.printStackTrace();
                    fail("Unable to read " + file.getAbsolutePath());
                }
          }
            final String text = new String(baos.toByteArray(), Charset.defaultCharset());
            final String[] lines = text.split("[\\r\\n]+");
            for (final String line : lines) {
              messages.remove(line);
            }
        }
        assertTrue("Log messages lost : " + messages.size(), messages.isEmpty());
        assertTrue("Files not rolled : " + files.length, files.length > 2);
    }
