	private static ConcurrentHashMap<Class, Integer> buildJdbcJavaClassMappings() {
		ConcurrentHashMap<Class, Integer> jdbcJavaClassMappings = new ConcurrentHashMap<Class, Integer>();

		// these mappings are the ones outlined specifically in the spec
		jdbcJavaClassMappings.put( String.class, Types.VARCHAR );
		jdbcJavaClassMappings.put( BigDecimal.class, Types.NUMERIC );
		jdbcJavaClassMappings.put( Boolean.class, Types.BIT );
		jdbcJavaClassMappings.put( Integer.class, Types.INTEGER );
		jdbcJavaClassMappings.put( Long.class, Types.BIGINT );
		jdbcJavaClassMappings.put( Float.class, Types.REAL );
		jdbcJavaClassMappings.put( Double.class, Types.DOUBLE );
		jdbcJavaClassMappings.put( byte[].class, Types.LONGVARBINARY );
		jdbcJavaClassMappings.put( java.sql.Date.class, Types.DATE );
		jdbcJavaClassMappings.put( Time.class, Types.TIME );
		jdbcJavaClassMappings.put( Timestamp.class, Types.TIMESTAMP );
		jdbcJavaClassMappings.put( Blob.class, Types.BLOB );
		jdbcJavaClassMappings.put( Clob.class, Types.CLOB );
		jdbcJavaClassMappings.put( Array.class, Types.ARRAY );
		jdbcJavaClassMappings.put( Struct.class, Types.STRUCT );
		jdbcJavaClassMappings.put( Ref.class, Types.REF );
		jdbcJavaClassMappings.put( Class.class, Types.JAVA_OBJECT );

		// additional "common sense" registrations
		jdbcJavaClassMappings.put( Character.class, Types.CHAR );
		jdbcJavaClassMappings.put( char[].class, Types.VARCHAR );
		jdbcJavaClassMappings.put( Character[].class, Types.VARCHAR );
		jdbcJavaClassMappings.put( Byte[].class, Types.LONGVARBINARY );
		jdbcJavaClassMappings.put( java.util.Date.class, Types.TIMESTAMP );
		jdbcJavaClassMappings.put( Calendar.class, Types.TIMESTAMP );

		return jdbcJavaClassMappings;
	}
