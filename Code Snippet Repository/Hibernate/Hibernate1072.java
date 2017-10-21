	private byte[] getByteCode(CtClass managedCtClass) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream( byteStream );
		try {
			managedCtClass.toBytecode( out );
			return byteStream.toByteArray();
		}
		catch (Exception e) {
			log.unableToTransformClass( e.getMessage() );
			throw new HibernateException( "Unable to transform class: " + e.getMessage() , e );
		}
		finally {
			try {
				out.close();
			}
			catch (IOException ignored) {
			}
		}
	}
