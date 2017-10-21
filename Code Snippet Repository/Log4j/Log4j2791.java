        @Override
        public CassandraManager createManager(final String name, final FactoryData data) {
            final Cluster.Builder builder = Cluster.builder()
                .addContactPointsWithPorts(data.contactPoints)
                .withClusterName(data.clusterName);
            if (data.useTls) {
                builder.withSSL();
            }
            if (Strings.isNotBlank(data.username)) {
                builder.withCredentials(data.username, data.password);
            }
            if (data.useClockForTimestampGenerator) {
                builder.withTimestampGenerator(new ClockTimestampGenerator());
            }
            final Cluster cluster = builder.build();

            final StringBuilder sb = new StringBuilder("INSERT INTO ").append(data.table).append(" (");
            for (final ColumnMapping column : data.columns) {
                sb.append(column.getName()).append(',');
            }
            sb.setCharAt(sb.length() - 1, ')');
            sb.append(" VALUES (");
            final List<ColumnMapping> columnMappings = new ArrayList<>(data.columns.length);
            for (final ColumnMapping column : data.columns) {
                if (Strings.isNotEmpty(column.getLiteralValue())) {
                    sb.append(column.getLiteralValue());
                } else {
                    sb.append('?');
                    columnMappings.add(column);
                }
                sb.append(',');
            }
            sb.setCharAt(sb.length() - 1, ')');
            final String insertQueryTemplate = sb.toString();
            LOGGER.debug("Using CQL for appender {}: {}", name, insertQueryTemplate);
            return new CassandraManager(name, data.getBufferSize(), cluster, data.keyspace, insertQueryTemplate,
                columnMappings, data.batched ? new BatchStatement(data.batchType) : null);
        }
