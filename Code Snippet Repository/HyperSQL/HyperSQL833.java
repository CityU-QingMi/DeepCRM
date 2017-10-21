        public void endElement(String namespaceURI, String localName,
                               String qName) throws SAXException {

            checkClosed();

            try {
                this.writer.writeEndElement();
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
