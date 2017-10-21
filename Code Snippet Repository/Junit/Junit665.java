	private void writeTestcase(TestIdentifier testIdentifier, NumberFormat numberFormat, XMLStreamWriter writer)
			throws XMLStreamException {

		writer.writeStartElement("testcase");

		writer.writeAttribute("name", getName(testIdentifier));
		writer.writeAttribute("classname", getClassName(testIdentifier));
		writer.writeAttribute("time", getTime(testIdentifier, numberFormat));
		newLine(writer);

		writeSkippedOrErrorOrFailureElement(testIdentifier, writer);
		writeReportEntriesToSystemOutElement(testIdentifier, writer);
		writeNonStandardAttributesToSystemOutElement(testIdentifier, writer);

		writer.writeEndElement();
		newLine(writer);
	}
