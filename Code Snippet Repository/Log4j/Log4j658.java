        @Override
        public MemoryMappedFileAppender build() {
            final String name = getName();
            final int actualRegionLength = determineValidRegionLength(name, regionLength);

            if (name == null) {
                LOGGER.error("No name provided for MemoryMappedFileAppender");
                return null;
            }

            if (fileName == null) {
                LOGGER.error("No filename provided for MemoryMappedFileAppender with name " + name);
                return null;
            }
            final Layout<? extends Serializable> layout = getOrCreateLayout();
            final MemoryMappedFileManager manager = MemoryMappedFileManager.getFileManager(fileName, append, isImmediateFlush(),
                    actualRegionLength, advertiseURI, layout);
            if (manager == null) {
                return null;
            }

            return new MemoryMappedFileAppender(name, layout, getFilter(), manager, fileName, isIgnoreExceptions(), false,
                    advertise ? getConfiguration().getAdvertiser() : null);
        }
