	protected RemoteInvocationResult readRemoteInvocationResult(InputStream is, @Nullable String codebaseUrl)
			throws IOException, ClassNotFoundException {

		ObjectInputStream ois = createObjectInputStream(decorateInputStream(is), codebaseUrl);
		try {
			return doReadRemoteInvocationResult(ois);
		}
		finally {
			ois.close();
		}
	}
