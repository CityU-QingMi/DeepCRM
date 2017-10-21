    public static FileManager getFileManager(final String fileName, final boolean append, boolean locking,
            final boolean bufferedIo, final boolean createOnDemand, final String advertiseUri,
            final Layout<? extends Serializable> layout,
            final int bufferSize, final String filePermissions, final String fileOwner, final String fileGroup,
            final Configuration configuration) {

        if (locking && bufferedIo) {
            locking = false;
        }
        return narrow(FileManager.class, getManager(fileName, new FactoryData(append, locking, bufferedIo, bufferSize,
                createOnDemand, advertiseUri, layout, filePermissions, fileOwner, fileGroup, configuration), FACTORY));
    }
