        @Override
        public RandomAccessFileManager createManager(final String name, final FactoryData data) {
            final File file = new File(name);
            if (!data.append) {
                file.delete();
            }

            final boolean writeHeader = !data.append || !file.exists();
            final OutputStream os = NullOutputStream.getInstance();
            RandomAccessFile raf;
            try {
                FileUtils.makeParentDirs(file);
                raf = new RandomAccessFile(name, "rw");
                if (data.append) {
                    raf.seek(raf.length());
                } else {
                    raf.setLength(0);
                }
                return new RandomAccessFileManager(data.getLoggerContext(), raf, name,
                        os, data.bufferSize, data.advertiseURI, data.layout, writeHeader);
            } catch (final Exception ex) {
                LOGGER.error("RandomAccessFileManager (" + name + ") " + ex, ex);
            }
            return null;
        }
