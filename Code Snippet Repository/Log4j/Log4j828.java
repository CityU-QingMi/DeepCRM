    public static boolean execute(final File source, final File destination, final boolean deleteSource)
            throws IOException {
        if (source.exists()) {
            try (final FileInputStream fis = new FileInputStream(source);
                    final BufferedOutputStream os = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(
                            destination)))) {
                final byte[] inbuf = new byte[BUF_SIZE];
                int n;

                while ((n = fis.read(inbuf)) != -1) {
                    os.write(inbuf, 0, n);
                }
            }

            if (deleteSource && !source.delete()) {
                LOGGER.warn("Unable to delete " + source.toString() + '.');
            }

            return true;
        }

        return false;
    }
