        public void startElement(String namespaceURI, String localName,
                                 String qName,
                                 Attributes atts) throws SAXException {

            checkClosed();

            try {
                int    qi     = qName.indexOf(':');
                String prefix = (qi > 0) ? qName.substring(0, qi)
                        : "";

                this.writer.writeStartElement(prefix, localName, namespaceURI);

                int length = namespaces.size();

                for (int i = 0; i < length; i++) {
                    QualifiedName ns = namespaces.get(i);

                    this.writer.writeNamespace(ns.prefix, ns.namespaceName);
                }
                namespaces.clear();

                length = atts.getLength();

                for (int i = 0; i < length; i++) {
                    this.writer.writeAttribute(atts.getURI(i),
                            atts.getLocalName(i), atts.getValue(i));
                }
            } catch (XMLStreamException e) {
                throw new SAXException(e);
            }
        }
