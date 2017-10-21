    public static RollingFileManager getFileManager(final String fileName, final String pattern, final boolean append,
            final boolean bufferedIO, final TriggeringPolicy policy, final RolloverStrategy strategy,
            final String advertiseURI, final Layout<? extends Serializable> layout, final int bufferSize,
            final boolean immediateFlush, final boolean createOnDemand,
            final String filePermissions, final String fileOwner, final String fileGroup,
            final Configuration configuration) {

        if (strategy instanceof DirectWriteRolloverStrategy && fileName != null) {
            LOGGER.error("The fileName attribute must not be specified with the DirectWriteRolloverStrategy");
            return null;
        }
        final String name = fileName == null ? pattern : fileName;
        return narrow(RollingFileManager.class, getManager(name, new FactoryData(fileName, pattern, append,
            bufferedIO, policy, strategy, advertiseURI, layout, bufferSize, immediateFlush, createOnDemand,
            filePermissions, fileOwner, fileGroup, configuration), factory));
    }
