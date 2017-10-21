        @Override
        public FileAppender build() {
            boolean bufferedIo = isBufferedIo();
            final int bufferSize = getBufferSize();
            if (locking && bufferedIo) {
                LOGGER.warn("Locking and buffering are mutually exclusive. No buffering will occur for {}", fileName);
                bufferedIo = false;
            }
            if (!bufferedIo && bufferSize > 0) {
                LOGGER.warn("The bufferSize is set to {} but bufferedIo is false: {}", bufferSize, bufferedIo);
            }
            final Layout<? extends Serializable> layout = getOrCreateLayout();

            final FileManager manager = FileManager.getFileManager(fileName, append, locking, bufferedIo, createOnDemand,
                    advertiseUri, layout, bufferSize, filePermissions, fileOwner, fileGroup, getConfiguration());
            if (manager == null) {
                return null;
            }

            return new FileAppender(getName(), layout, getFilter(), manager, fileName, isIgnoreExceptions(),
                    !bufferedIo || isImmediateFlush(), advertise ? getConfiguration().getAdvertiser() : null);
        }
