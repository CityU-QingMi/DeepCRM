        public FactoryData(final boolean append, final boolean locking, final boolean bufferedIo, final int bufferSize,
                final boolean createOnDemand, final String advertiseURI, final Layout<? extends Serializable> layout,
                final String filePermissions, final String fileOwner, final String fileGroup,
                final Configuration configuration) {
            super(configuration);
            this.append = append;
            this.locking = locking;
            this.bufferedIo = bufferedIo;
            this.bufferSize = bufferSize;
            this.createOnDemand = createOnDemand;
            this.advertiseURI = advertiseURI;
            this.layout = layout;
            this.filePermissions = filePermissions;
            this.fileOwner = fileOwner;
            this.fileGroup = fileGroup;
        }
