    private CassandraManager(final String name, final int bufferSize, final Cluster cluster,
                             final String keyspace, final String insertQueryTemplate,
                             final List<ColumnMapping> columnMappings, final BatchStatement batchStatement) {
        super(name, bufferSize);
        this.cluster = cluster;
        this.keyspace = keyspace;
        this.insertQueryTemplate = insertQueryTemplate;
        this.columnMappings = columnMappings;
        this.batchStatement = batchStatement;
        this.values = new Object[columnMappings.size()];
    }
