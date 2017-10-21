        public void processingInstruction(String target,
                String data) throws SAXException {

            checkClosed();

            try {
                this.writer.writeProcessingInstruction(target, data);
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
