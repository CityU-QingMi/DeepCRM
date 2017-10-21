	@Override
	public InputStream getBody() throws IOException {
		if (this.multipartRequest instanceof StandardMultipartHttpServletRequest) {
			try {
				return this.multipartRequest.getPart(this.partName).getInputStream();
			}
			catch (Exception ex) {
				throw new MultipartException("Could not parse multipart servlet request", ex);
			}
		}
		else {
			MultipartFile file = this.multipartRequest.getFile(this.partName);
			if (file != null) {
				return file.getInputStream();
			}
			else {
				String paramValue = this.multipartRequest.getParameter(this.partName);
				return new ByteArrayInputStream(paramValue.getBytes(determineCharset()));
			}
		}
	}
