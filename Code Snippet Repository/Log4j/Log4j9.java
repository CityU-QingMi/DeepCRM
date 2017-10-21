    private void run() {
        if (cla.getRecurseIntoPath() != null) {
            final AtomicInteger countOKs = new AtomicInteger();
            final AtomicInteger countFails = new AtomicInteger();
            try {
                Files.walkFileTree(cla.getRecurseIntoPath(), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                            throws IOException {
                        if (cla.getPathIn() == null || file.getFileName().equals(cla.getPathIn())) {
                            verbose("Reading %s", file);
                            String newFile = file.getFileName().toString();
                            final int lastIndex = newFile.lastIndexOf(".");
                            newFile = lastIndex < 0 ? newFile + FILE_EXT_XML
                                    : newFile.substring(0, lastIndex) + FILE_EXT_XML;
                            final Path resolved = file.resolveSibling(newFile);
                            try (final InputStream input = new InputStreamWrapper(Files.newInputStream(file), file.toString());
                                    final OutputStream output = Files.newOutputStream(resolved)) {
                                try {
                                    convert(input, output);
                                    countOKs.incrementAndGet();
                                } catch (ConfigurationException | IOException e) {
                                    countFails.incrementAndGet();
                                    if (cla.isFailFast()) {
                                        throw e;
                                    }
                                    e.printStackTrace();
                                }
                                verbose("Wrote %s", resolved);
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (final IOException e) {
                throw new ConfigurationException(e);
            } finally {
                verbose("OK = %,d, Failures = %,d, Total = %,d", countOKs.get(), countFails.get(),
                        countOKs.get() + countFails.get());
            }
        } else {
            verbose("Reading %s", cla.getPathIn());
            try (final InputStream input = getInputStream(); final OutputStream output = getOutputStream()) {
                convert(input, output);
            } catch (final IOException e) {
                throw new ConfigurationException(e);
            }
            verbose("Wrote %s", cla.getPathOut());
        }
    }
