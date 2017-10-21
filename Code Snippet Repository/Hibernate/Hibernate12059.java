	private boolean isLob(String typeName) {
		return "blob".equals( typeName )
				|| "clob".equals( typeName )
				|| "nclob".equals( typeName )
				|| Blob.class.getName().equals( typeName )
				|| Clob.class.getName().equals( typeName )
				|| NClob.class.getName().equals( typeName )
				|| BlobType.class.getName().equals( typeName )
				|| ClobType.class.getName().equals( typeName )
				|| NClobType.class.getName().equals( typeName );
	}
