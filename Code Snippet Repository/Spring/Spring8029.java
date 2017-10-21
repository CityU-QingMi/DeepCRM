	private Object transformAndUnmarshal(Source source, @Nullable String encoding) throws IOException {
		try {
			Transformer transformer = this.transformerFactory.newTransformer();
			if (encoding != null) {
				transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			}
			ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
			transformer.transform(source, new StreamResult(os));
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			return unmarshalInputStream(is);
		}
		catch (TransformerException ex) {
			throw new MarshallingFailureException(
					"Could not transform from [" + ClassUtils.getShortName(source.getClass()) + "]", ex);
		}
	}
