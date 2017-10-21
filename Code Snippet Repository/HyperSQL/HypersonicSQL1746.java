        public void characters(char[] ch, int start,
                               int length) throws SAXException {

            checkClosed();

            try {
                this.writer.writeCharacters(ch, start, length);
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
