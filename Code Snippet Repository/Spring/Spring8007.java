	@Override
	public Object unmarshal(Source source, @Nullable MimeContainer mimeContainer) throws XmlMappingException {
		source = processSource(source);

		try {
			Unmarshaller unmarshaller = createUnmarshaller();
			if (this.mtomEnabled && mimeContainer != null) {
				unmarshaller.setAttachmentUnmarshaller(new Jaxb2AttachmentUnmarshaller(mimeContainer));
			}
			if (StaxUtils.isStaxSource(source)) {
				return unmarshalStaxSource(unmarshaller, source);
			}
			else if (this.mappedClass != null) {
				return unmarshaller.unmarshal(source, this.mappedClass).getValue();
			}
			else {
				return unmarshaller.unmarshal(source);
			}
		}
		catch (NullPointerException ex) {
			if (!isSupportDtd()) {
				throw new UnmarshallingFailureException("NPE while unmarshalling: " +
						"This can happen due to the presence of DTD declarations which are disabled.", ex);
			}
			throw ex;
		}
		catch (JAXBException ex) {
			throw convertJaxbException(ex);
		}
	}
