        private FactoryData(final SocketAddress[] contactPoints, final ColumnMapping[] columns, final boolean useTls,
                            final String clusterName, final String keyspace, final String table, final String username,
                            final String password, final boolean useClockForTimestampGenerator, final int bufferSize,
                            final boolean batched, final BatchStatement.Type batchType) {
            super(bufferSize);
            this.contactPoints = convertAndAddDefaultPorts(contactPoints);
            this.columns = columns;
            this.useTls = useTls;
            this.clusterName = clusterName;
            this.keyspace = keyspace;
            this.table = table;
            this.username = username;
            this.password = password;
            this.useClockForTimestampGenerator = useClockForTimestampGenerator;
            this.batched = batched;
            this.batchType = batchType;
        }
