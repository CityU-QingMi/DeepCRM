    protected void defineAttributeView(final Path path) {
        if (attributeViewEnabled) {
            try {
                // FileOutputStream may not create new file on all jvm
                path.toFile().createNewFile();

                FileUtils.defineFilePosixAttributeView(path, filePermissions, fileOwner, fileGroup);
            } catch (final Exception e) {
                LOGGER.error("Could not define attribute view on path \"{}\" got {}", path, e.getMessage(), e);
            }
        }
    }
