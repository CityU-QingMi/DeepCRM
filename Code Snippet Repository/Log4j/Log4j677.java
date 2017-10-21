        @Override
        public RandomAccessFileAppender build() {
            final String name = getName();
            if (name == null) {
                LOGGER.error("No name provided for FileAppender");
                return null;
            }

            if (fileName == null) {
                LOGGER.error("No filename provided for FileAppender with name " + name);
                return null;
            }
            final Layout<? extends Serializable> layout = getOrCreateLayout();
            final boolean immediateFlush = isImmediateFlush();
            final RandomAccessFileManager manager = RandomAccessFileManager.getFileManager(fileName, append,
                    immediateFlush, getBufferSize(), advertiseURI, layout, null);
            if (manager == null) {
                return null;
            }

            return new RandomAccessFileAppender(name, layout, getFilter(), manager, fileName, isIgnoreExceptions(),
                    immediateFlush, advertise ? getConfiguration().getAdvertiser() : null);
        }
