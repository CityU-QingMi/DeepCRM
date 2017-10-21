    @Override
    public void writeXmlConfiguration(final OutputStream output) throws IOException {
        try {
            final XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
            writeXmlConfiguration(xmlWriter);
            xmlWriter.close();
        } catch (final XMLStreamException e) {
            if (e.getNestedException() instanceof IOException) {
                throw (IOException)e.getNestedException();
            }
            Throwables.rethrow(e);
        }
    }
