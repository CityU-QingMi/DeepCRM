    public static boolean execute(final String name, final File source, final File destination,
            final boolean deleteSource) throws IOException {
        if (!source.exists()) {
            return false;
        }
        LOGGER.debug("Starting {} compression of {}", name, source.getPath() );
        try (final FileInputStream input = new FileInputStream(source);
                final BufferedOutputStream output = new BufferedOutputStream(
                        new CompressorStreamFactory().createCompressorOutputStream(name, new FileOutputStream(
                                destination)))) {
            IOUtils.copy(input, output, BUF_SIZE);
            LOGGER.debug("Finished {} compression of {}", name, source.getPath() );
        } catch (final CompressorException e) {
            throw new IOException(e);
        }

        if (deleteSource) {
            try {
                if (Files.deleteIfExists(source.toPath())) {
                    LOGGER.debug("Deleted {}", source.toString());
                } else {
                    LOGGER.warn("Unable to delete {} after {} compression. File did not exist", source.toString(), name);
                }
            } catch (final Exception ex) {
                LOGGER.warn("Unable to delete {} after {} compression, {}", source.toString(), name, ex.getMessage());
            }
        }

        return true;
    }
