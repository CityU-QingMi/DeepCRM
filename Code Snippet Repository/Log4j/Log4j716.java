        @Override
        public JdbcAppender build() {
            if (Assert.isEmpty(columnConfigs) && Assert.isEmpty(columnMappings)) {
                LOGGER.error("Cannot create JdbcAppender without any columns configured.");
                return null;
            }
            final String managerName = "JdbcManager{name=" + getName() + ", bufferSize=" + bufferSize + ", tableName=" +
                tableName + ", columnConfigs=" + Arrays.toString(columnConfigs) + ", columnMappings=" +
                Arrays.toString(columnMappings) + '}';
            final JdbcDatabaseManager manager = JdbcDatabaseManager.getManager(managerName, bufferSize,
                connectionSource, tableName, columnConfigs, columnMappings);
            if (manager == null) {
                return null;
            }
            return new JdbcAppender(getName(), getFilter(), isIgnoreExceptions(), manager);
        }
