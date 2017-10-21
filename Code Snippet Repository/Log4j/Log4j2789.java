    @Override
    protected void writeInternal(final LogEvent event) {
        for (int i = 0; i < columnMappings.size(); i++) {
            final ColumnMapping columnMapping = columnMappings.get(i);
            if (ThreadContextMap.class.isAssignableFrom(columnMapping.getType())
                || ReadOnlyStringMap.class.isAssignableFrom(columnMapping.getType())) {
                values[i] = event.getContextData().toMap();
            } else if (ThreadContextStack.class.isAssignableFrom(columnMapping.getType())) {
                values[i] = event.getContextStack().asList();
            } else if (Date.class.isAssignableFrom(columnMapping.getType())) {
                values[i] = DateTypeConverter.fromMillis(event.getTimeMillis(), columnMapping.getType().asSubclass(Date.class));
            } else {
                values[i] = TypeConverters.convert(columnMapping.getLayout().toSerializable(event),
                    columnMapping.getType(), null);
            }
        }
        final BoundStatement boundStatement = preparedStatement.bind(values);
        if (batchStatement == null) {
            session.execute(boundStatement);
        } else {
            batchStatement.add(boundStatement);
        }
    }
