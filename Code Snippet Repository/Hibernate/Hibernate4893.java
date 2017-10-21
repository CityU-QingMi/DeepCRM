	@Override
	public void release() {
		if ( writer != null ) {
			try {
				writer.close();
			}
			catch (IOException e) {
				throw new SchemaManagementException( "Unable to close file writer : " + e.toString() );
			}
			finally {
				writer = null;
			}
		}
	}
