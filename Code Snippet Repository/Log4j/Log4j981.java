    private void writeXmlComponent(final XMLStreamWriter xmlWriter, final Component component, final int nesting) throws XMLStreamException {
        if (!component.getComponents().isEmpty() || component.getValue() != null) {
            writeXmlIndent(xmlWriter, nesting);
            xmlWriter.writeStartElement(component.getPluginType());
            writeXmlAttributes(xmlWriter, component);
            if (!component.getComponents().isEmpty()) {
                xmlWriter.writeCharacters(EOL);
            }
            for (final Component subComponent : component.getComponents()) {
                writeXmlComponent(xmlWriter, subComponent, nesting + 1);
            }
            if (component.getValue() != null) {
                xmlWriter.writeCharacters(component.getValue());
            }
            if (!component.getComponents().isEmpty()) {
                writeXmlIndent(xmlWriter, nesting);
            }
            xmlWriter.writeEndElement();
        } else {
            writeXmlIndent(xmlWriter, nesting);
            xmlWriter.writeEmptyElement(component.getPluginType());
            writeXmlAttributes(xmlWriter, component);
        }
        xmlWriter.writeCharacters(EOL);
    }
