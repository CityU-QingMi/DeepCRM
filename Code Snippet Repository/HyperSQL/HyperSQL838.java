        public void comment(char[] ch, int start,
                            int length) throws SAXException {

            checkClosed();

            try {
                this.writer.writeComment(new String(ch, start, length));
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
