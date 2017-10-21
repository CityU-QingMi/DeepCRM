        @SuppressWarnings("")
        @Override
        public MemoryMappedFileManager createManager(final String name, final FactoryData data) {
            final File file = new File(name);
            if (!data.append) {
                file.delete();
            }

            final boolean writeHeader = !data.append || !file.exists();
            final OutputStream os = NullOutputStream.getInstance();
            RandomAccessFile raf = null;
            try {
                FileUtils.makeParentDirs(file);
                raf = new RandomAccessFile(name, "rw");
                final long position = (data.append) ? raf.length() : 0;
                raf.setLength(position + data.regionLength);
                return new MemoryMappedFileManager(raf, name, os, data.immediateFlush, position, data.regionLength,
                        data.advertiseURI, data.layout, writeHeader);
            } catch (final Exception ex) {
                LOGGER.error("MemoryMappedFileManager (" + name + ") " + ex, ex);
                Closer.closeSilently(raf);
            }
            return null;
        }
