        private void setup() {
            try {
                TransformerHandler handler = this.factory.newTransformerHandler();
                nextHandler = handler;
                if (this.parentNode != null) {
                    this.result = new DOMResult(this.parentNode);
                } else {
                    this.result = new DOMResult();
                }
                handler.setResult(this.result);
            } catch (javax.xml.transform.TransformerException local) {
                throw new XWorkException("Fatal-Error: Unable to get transformer handler", local);
            }
        }
