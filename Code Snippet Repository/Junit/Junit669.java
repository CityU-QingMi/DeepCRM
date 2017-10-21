	private void writeReportEntriesToSystemOutElement(TestIdentifier testIdentifier, XMLStreamWriter writer)
			throws XMLStreamException {

		List<ReportEntry> entries = this.reportData.getReportEntries(testIdentifier);
		if (!entries.isEmpty()) {
			writer.writeStartElement("system-out");
			newLine(writer);
			for (int i = 0; i < entries.size(); i++) {
				writer.writeCharacters(buildReportEntryDescription(entries.get(i), i + 1));
			}
			writer.writeEndElement();
			newLine(writer);
		}
	}
