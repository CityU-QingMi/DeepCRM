        public FactoryData(final String fileName, final String pattern, final boolean append, final boolean immediateFlush,
                final int bufferSize, final TriggeringPolicy policy, final RolloverStrategy strategy,
                final String advertiseURI, final Layout<? extends Serializable> layout,
                final String filePermissions, final String fileOwner, final String fileGroup,
                final Configuration configuration) {
            super(configuration);
            this.fileName = fileName;
            this.pattern = pattern;
            this.append = append;
            this.immediateFlush = immediateFlush;
            this.bufferSize = bufferSize;
            this.policy = policy;
            this.strategy = strategy;
            this.advertiseURI = advertiseURI;
            this.layout = layout;
            this.filePermissions = filePermissions;
            this.fileOwner = fileOwner;
            this.fileGroup = fileGroup;
        }
