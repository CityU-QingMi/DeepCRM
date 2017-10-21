	protected Object unmarshalDomSource(DOMSource domSource) throws XmlMappingException {
		if (domSource.getNode() == null) {
			domSource.setNode(buildDocument());
		}
		try {
			return unmarshalDomNode(domSource.getNode());
		}
		catch (NullPointerException ex) {
			if (!isSupportDtd()) {
				throw new UnmarshallingFailureException("NPE while unmarshalling. " +
						"This can happen on JDK 1.6 due to the presence of DTD " +
						"declarations, which are disabled.", ex);
			}
			throw ex;
		}
	}
