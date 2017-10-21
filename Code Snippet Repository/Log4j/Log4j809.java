    public static RollingRandomAccessFileManager getRollingRandomAccessFileManager(final String fileName,
            final String filePattern, final boolean isAppend, final boolean immediateFlush, final int bufferSize,
            final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI,
            final Layout<? extends Serializable> layout, final String filePermissions, final String fileOwner, final String fileGroup,
            final Configuration configuration) {
        if (strategy instanceof DirectWriteRolloverStrategy && fileName != null) {
            LOGGER.error("The fileName attribute must not be specified with the DirectWriteRolloverStrategy");
            return null;
        }
        final String name = fileName == null ? filePattern : fileName;
        return narrow(RollingRandomAccessFileManager.class, getManager(name, new FactoryData(fileName, filePattern, isAppend,
                immediateFlush, bufferSize, policy, strategy, advertiseURI, layout,
                filePermissions, fileOwner, fileGroup, configuration), FACTORY));
    }
