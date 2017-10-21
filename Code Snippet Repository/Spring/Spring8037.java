	private void transformAndMarshal(Object graph, Result result) throws IOException {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
			marshalOutputStream(graph, os);
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			Transformer transformer = this.transformerFactory.newTransformer();
			transformer.transform(new StreamSource(is), result);
		}
		catch (TransformerException ex) {
			throw new MarshallingFailureException(
					"Could not transform to [" + ClassUtils.getShortName(result.getClass()) + "]", ex);
		}

	}
