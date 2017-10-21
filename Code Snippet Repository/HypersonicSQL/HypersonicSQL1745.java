        public void endDocument() throws SAXException {

            checkClosed();

            try {
                this.writer.writeEndDocument();
                this.writer.flush();
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
