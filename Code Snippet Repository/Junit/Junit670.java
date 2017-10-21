	private void writeNonStandardAttributesToSystemOutElement(TestIdentifier testIdentifier, XMLStreamWriter writer)
			throws XMLStreamException {

		String cData = "\nunique-id: " + testIdentifier.getUniqueId() //
				+ "\ndisplay-name: " + testIdentifier.getDisplayName() + "\n";

		writer.writeStartElement("system-out");
		writer.writeCData(cData);
		writer.writeEndElement();
		newLine(writer);
	}
