	private static byte[] digest(String algorithm, InputStream inputStream) throws IOException {
		MessageDigest messageDigest = getDigest(algorithm);
		if (inputStream instanceof UpdateMessageDigestInputStream){
			((UpdateMessageDigestInputStream) inputStream).updateMessageDigest(messageDigest);
			return messageDigest.digest();
		}
		else {
			final byte[] buffer = new byte[StreamUtils.BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				messageDigest.update(buffer, 0, bytesRead);
			}
			return messageDigest.digest();
		}
	}
