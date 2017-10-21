    @Override
    public String toXmlConfiguration() {
        final StringWriter sw = new StringWriter();
        try {
            final XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(sw);
            writeXmlConfiguration(xmlWriter);
            xmlWriter.close();
        } catch (final XMLStreamException e) {
            Throwables.rethrow(e);
        }
        return sw.toString();
    }
