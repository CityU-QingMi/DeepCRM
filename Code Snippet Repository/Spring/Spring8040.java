	protected Object unmarshalStreamSource(StreamSource streamSource) throws XmlMappingException, IOException {
		if (streamSource.getInputStream() != null) {
			if (isProcessExternalEntities() && isSupportDtd()) {
				return unmarshalInputStream(streamSource.getInputStream());
			}
			else {
				InputSource inputSource = new InputSource(streamSource.getInputStream());
				inputSource.setEncoding(getDefaultEncoding());
				return unmarshalSaxSource(new SAXSource(inputSource));
			}
		}
		else if (streamSource.getReader() != null) {
			if (isProcessExternalEntities() && isSupportDtd()) {
				return unmarshalReader(streamSource.getReader());
			}
			else {
				return unmarshalSaxSource(new SAXSource(new InputSource(streamSource.getReader())));
			}
		}
		else {
			return unmarshalSaxSource(new SAXSource(new InputSource(streamSource.getSystemId())));
		}
	}
