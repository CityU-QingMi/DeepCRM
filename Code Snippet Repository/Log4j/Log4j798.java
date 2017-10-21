        @Override
        public RollingFileManager createManager(final String name, final FactoryData data) {
            long size = 0;
            boolean writeHeader = !data.append;
            File file = null;
            if (data.fileName != null) {
                file = new File(data.fileName);
                // LOG4J2-1140: check writeHeader before creating the file
                writeHeader = !data.append || !file.exists();

                try {
                    FileUtils.makeParentDirs(file);
                    final boolean created = data.createOnDemand ? false : file.createNewFile();
                    LOGGER.trace("New file '{}' created = {}", name, created);
                } catch (final IOException ioe) {
                    LOGGER.error("Unable to create file " + name, ioe);
                    return null;
                }
                size = data.append ? file.length() : 0;
            }

            try {
                final int actualSize = data.bufferedIO ? data.bufferSize : Constants.ENCODER_BYTE_BUFFER_SIZE;
                final ByteBuffer buffer = ByteBuffer.wrap(new byte[actualSize]);
                final OutputStream os = data.createOnDemand  || data.fileName == null ? null :
                        new FileOutputStream(data.fileName, data.append);
                final long time = data.createOnDemand || file == null ?
                        System.currentTimeMillis() : file.lastModified(); // LOG4J2-531 create file first so time has valid value

                final RollingFileManager rm = new RollingFileManager(data.getLoggerContext(), data.fileName, data.pattern, os,
                    data.append, data.createOnDemand, size, time, data.policy, data.strategy, data.advertiseURI,
                    data.layout, data.filePermissions, data.fileOwner, data.fileGroup, writeHeader, buffer);
                if (os != null && rm.isAttributeViewEnabled()) {
                    rm.defineAttributeView(file.toPath());
                }

                return rm;
            } catch (final IOException ex) {
                LOGGER.error("RollingFileManager (" + name + ") " + ex, ex);
            }
            return null;
        }
