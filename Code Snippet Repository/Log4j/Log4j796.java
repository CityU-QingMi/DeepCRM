        public FactoryData(final String fileName, final String pattern, final boolean append, final boolean bufferedIO,
                final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI,
                final Layout<? extends Serializable> layout, final int bufferSize, final boolean immediateFlush,
                final boolean createOnDemand, final String filePermissions, final String fileOwner, final String fileGroup,
                final Configuration configuration) {
            super(configuration);
            this.fileName = fileName;
            this.pattern = pattern;
            this.append = append;
            this.bufferedIO = bufferedIO;
            this.bufferSize = bufferSize;
            this.policy = policy;
            this.strategy = strategy;
            this.advertiseURI = advertiseURI;
            this.layout = layout;
            this.immediateFlush = immediateFlush;
            this.createOnDemand = createOnDemand;
            this.filePermissions = filePermissions;
            this.fileOwner = fileOwner;
            this.fileGroup = fileGroup;
        }
