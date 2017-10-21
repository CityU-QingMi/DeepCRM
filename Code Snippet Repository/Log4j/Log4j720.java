        @Override
        public JdbcDatabaseManager createManager(final String name, final FactoryData data) {
            final StringBuilder sb = new StringBuilder("INSERT INTO ").append(data.tableName).append(" (");
            // so this gets a little more complicated now that there are two ways to configure column mappings, but
            // both mappings follow the same exact pattern for the prepared statement
            for (final ColumnMapping mapping : data.columnMappings) {
                sb.append(mapping.getName()).append(',');
            }
            for (final ColumnConfig config : data.columnConfigs) {
                sb.append(config.getColumnName()).append(',');
            }
            // at least one of those arrays is guaranteed to be non-empty
            sb.setCharAt(sb.length() - 1, ')');
            sb.append(" VALUES (");
            final List<ColumnMapping> columnMappings = new ArrayList<>(data.columnMappings.length);
            for (final ColumnMapping mapping : data.columnMappings) {
                if (Strings.isNotEmpty(mapping.getLiteralValue())) {
                    sb.append(mapping.getLiteralValue());
                } else {
                    sb.append('?');
                    columnMappings.add(mapping);
                }
                sb.append(',');
            }
            final List<ColumnConfig> columnConfigs = new ArrayList<>(data.columnConfigs.length);
            for (final ColumnConfig config : data.columnConfigs) {
                if (Strings.isNotEmpty(config.getLiteralValue())) {
                    sb.append(config.getLiteralValue());
                } else {
                    sb.append('?');
                    columnConfigs.add(config);
                }
                sb.append(',');
            }
            // at least one of those arrays is guaranteed to be non-empty
            sb.setCharAt(sb.length() - 1, ')');
            final String sqlStatement = sb.toString();

            return new JdbcDatabaseManager(name, data.getBufferSize(), data.connectionSource, sqlStatement,
                columnConfigs, columnMappings);
        }
