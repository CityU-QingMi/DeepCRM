        @Override
        public FileManager createManager(final String name, final FactoryData data) {
            final File file = new File(name);
            try {
                FileUtils.makeParentDirs(file);
                final boolean writeHeader = !data.append || !file.exists();
                final int actualSize = data.bufferedIo ? data.bufferSize : Constants.ENCODER_BYTE_BUFFER_SIZE;
                final ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[actualSize]);
                final FileOutputStream fos = data.createOnDemand ? null : new FileOutputStream(file, data.append);
                final FileManager fm = new FileManager(data.getLoggerContext(), name, fos, data.append, data.locking,
                        data.createOnDemand, data.advertiseURI, data.layout,
                        data.filePermissions, data.fileOwner, data.fileGroup, writeHeader, byteBuffer);
                if (fos != null && fm.attributeViewEnabled) {
                    fm.defineAttributeView(file.toPath());
                }
                return fm;
            } catch (final IOException ex) {
                LOGGER.error("FileManager (" + name + ") " + ex, ex);
            }
            return null;
        }
