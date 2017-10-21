	    private static WriteConcern toWriteConcern(final String writeConcernConstant,
	            final String writeConcernConstantClassName) {
	        WriteConcern writeConcern;
	        if (Strings.isNotEmpty(writeConcernConstant)) {
	            if (Strings.isNotEmpty(writeConcernConstantClassName)) {
	                try {
	                    final Class<?> writeConcernConstantClass = LoaderUtil.loadClass(writeConcernConstantClassName);
	                    final Field field = writeConcernConstantClass.getField(writeConcernConstant);
	                    writeConcern = (WriteConcern) field.get(null);
	                } catch (final Exception e) {
	                    LOGGER.error("Write concern constant [{}.{}] not found, using default.",
	                            writeConcernConstantClassName, writeConcernConstant);
	                    writeConcern = DEFAULT_WRITE_CONCERN;
	                }
	            } else {
	                writeConcern = WriteConcern.valueOf(writeConcernConstant);
	                if (writeConcern == null) {
	                    LOGGER.warn("Write concern constant [{}] not found, using default.", writeConcernConstant);
	                    writeConcern = DEFAULT_WRITE_CONCERN;
	                }
	            }
	        } else {
	            writeConcern = DEFAULT_WRITE_CONCERN;
	        }
	        return writeConcern;
	    }
