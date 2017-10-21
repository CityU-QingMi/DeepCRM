        public void processingInstruction(String target,
                String data) throws SAXException {

            checkClosed();

            ProcessingInstruction processingInstruction;

            processingInstruction =
                getDocument().createProcessingInstruction(target, data);

            getCurrentNode().appendChild(processingInstruction);
        }
