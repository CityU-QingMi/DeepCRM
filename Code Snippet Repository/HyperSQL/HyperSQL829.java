        public void startDocument() throws SAXException {

            checkClosed();

            try {
                this.writer.writeStartDocument();
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
