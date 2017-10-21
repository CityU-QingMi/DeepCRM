        public void startPrefixMapping(String prefix,
                                       String uri) throws SAXException {

            checkClosed();

            try {
                this.writer.setPrefix(prefix, uri);
                namespaces.add(new QualifiedName(prefix, uri));
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
