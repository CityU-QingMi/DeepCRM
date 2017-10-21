		private Part createPart(StreamStorage storage, HttpHeaders httpHeaders) {
			String filename = MultipartUtils.getFileName(httpHeaders);
			if (filename != null) {
				return new SynchronossFilePart(httpHeaders, storage, this.bufferFactory, filename);
			}
			else if (MultipartUtils.isFormField(httpHeaders, this.context)) {
				String value = MultipartUtils.readFormParameterValue(storage, httpHeaders);
				return new SynchronossFormFieldPart(httpHeaders, this.bufferFactory, value);
			}
			else {
				return new DefaultSynchronossPart(httpHeaders, storage, this.bufferFactory);
			}
		}
