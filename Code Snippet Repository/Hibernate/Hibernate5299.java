	private LobTypeMappings() {
		this.lobCodeByNonLobCode =  new BoundedConcurrentHashMap<Integer, Integer>();

		// BLOB mappings
		this.lobCodeByNonLobCode.put( Types.BLOB, Types.BLOB );
		this.lobCodeByNonLobCode.put( Types.BINARY, Types.BLOB );
		this.lobCodeByNonLobCode.put( Types.VARBINARY, Types.BLOB );
		this.lobCodeByNonLobCode.put( Types.LONGVARBINARY, Types.BLOB );

		// CLOB mappings
		this.lobCodeByNonLobCode.put( Types.CLOB, Types.CLOB );
		this.lobCodeByNonLobCode.put( Types.CHAR, Types.CLOB );
		this.lobCodeByNonLobCode.put( Types.VARCHAR, Types.CLOB );
		this.lobCodeByNonLobCode.put( Types.LONGVARCHAR, Types.CLOB );

		// NCLOB mappings
		this.lobCodeByNonLobCode.put( Types.NCLOB, Types.NCLOB );
		this.lobCodeByNonLobCode.put( Types.NCHAR, Types.NCLOB );
		this.lobCodeByNonLobCode.put( Types.NVARCHAR, Types.NCLOB );
		this.lobCodeByNonLobCode.put( Types.LONGNVARCHAR, Types.NCLOB );
	}
