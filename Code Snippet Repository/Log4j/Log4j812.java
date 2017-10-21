        @Override
        public RollingRandomAccessFileManager createManager(final String name, final FactoryData data) {
            File file = null;
            long size = 0;
            long time = System.currentTimeMillis();
            RandomAccessFile raf = null;
            if (data.fileName != null) {
                file = new File(name);

                if (!data.append) {
                    file.delete();
                }
                size = data.append ? file.length() : 0;
                if (file.exists()) {
                    time = file.lastModified();
                }
                try {
                    FileUtils.makeParentDirs(file);
                    raf = new RandomAccessFile(name, "rw");
                    if (data.append) {
                        final long length = raf.length();
                        LOGGER.trace("RandomAccessFile {} seek to {}", name, length);
                        raf.seek(length);
                    } else {
                        LOGGER.trace("RandomAccessFile {} set length to 0", name);
                        raf.setLength(0);
                    }
                } catch (final IOException ex) {
                    LOGGER.error("Cannot access RandomAccessFile " + ex, ex);
                    if (raf != null) {
                        try {
                            raf.close();
                        } catch (final IOException e) {
                            LOGGER.error("Cannot close RandomAccessFile {}", name, e);
                        }
                    }
                    return null;
                }
            }
            final boolean writeHeader = !data.append || file == null || !file.exists();

            final RollingRandomAccessFileManager rrm = new RollingRandomAccessFileManager(data.getLoggerContext(), raf, name, data.pattern,
                    NullOutputStream.getInstance(), data.append, data.immediateFlush, data.bufferSize, size, time, data.policy,
                    data.strategy, data.advertiseURI, data.layout, data.filePermissions, data.fileOwner, data.fileGroup, writeHeader);
            if (rrm.isAttributeViewEnabled()) {
                rrm.defineAttributeView(file.toPath());
            }
            return rrm;
        }
