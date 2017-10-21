	@Override
	public final void startDTD(String name, @Nullable String publicId, String systemId) throws SAXException {
		try {
			StringBuilder builder = new StringBuilder("<!DOCTYPE ");
			builder.append(name);
			if (publicId != null) {
				builder.append(" PUBLIC \"");
				builder.append(publicId);
				builder.append("\" \"");
			}
			else {
				builder.append(" SYSTEM \"");
			}
			builder.append(systemId);
			builder.append("\">");

			dtdInternal(builder.toString());
		}
		catch (XMLStreamException ex) {
			throw new SAXException("Could not handle startDTD: " + ex.getMessage(), ex);
		}
	}
