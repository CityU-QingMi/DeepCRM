    @Test
    public void testPolicy() throws Exception {
        final Configuration configuration = new DefaultConfiguration();
        final Path target = Paths.get(TARGET_FILE);
        target.toFile().getParentFile().mkdirs();
        final long timeStamp = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
        final String expectedDate = formatter.format(timeStamp);
        final String rolledFileName = ROLLED_FILE_PREFIX + expectedDate + ROLLED_FILE_SUFFIX;
        final Path rolled = Paths.get(rolledFileName);
        final long copied;
        try (final InputStream is = new ByteArrayInputStream(TEST_DATA.getBytes("UTF-8"))) {
            copied = Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
        }
        final long size = Files.size(target);
        assertTrue(size > 0);
        assertEquals(copied, size);

        Assert.assertTrue(target.toFile().setLastModified(timeStamp));
        final PatternLayout layout = PatternLayout.newBuilder().withPattern("%msg").withConfiguration(configuration)
                .build();
        final RolloverStrategy strategy = DefaultRolloverStrategy.createStrategy(null, null, null, "0", null, true,
                configuration);
        final OnStartupTriggeringPolicy policy = OnStartupTriggeringPolicy.createPolicy(1);
        try (final RollingFileManager manager = RollingFileManager.getFileManager(TARGET_FILE, TARGET_PATTERN, true, false,
                policy, strategy, null, layout, 8192, true, false, null, null, null, configuration)) {
            manager.initialize();
            final String files = Arrays.toString(new File(TARGET_FOLDER).listFiles());
            assertTrue(target.toString() + ", files = " + files, Files.exists(target));
            assertEquals(target.toString(), 0, Files.size(target));
            assertTrue("Missing: " + rolled.toString() + ", files on disk = " + files, Files.exists(rolled));
            assertEquals(rolled.toString(), size, Files.size(rolled));
        }
    }
