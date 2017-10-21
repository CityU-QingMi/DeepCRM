		@Override
		public void merge(InputStream input, Charset charset, MediaType contentType,
				ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {

			if (contentType.isCompatibleWith(APPLICATION_JSON)) {
				InputStreamReader reader = new InputStreamReader(input, charset);
				this.parser.merge(reader, builder);
			}
			else {
				throw new IOException("protobuf-java-util does not support " + contentType + " format");
			}
		}
