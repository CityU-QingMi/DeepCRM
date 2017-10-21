        public void adjustJavaLines(final int offset) {

            if (node != null) {
                adjustJavaLine(node, offset);
            }

            if (body != null) {
                try {
                    body.visit(new Node.Visitor() {

                        public void doVisit(Node n) {
                            adjustJavaLine(n, offset);
                        }

                        public void visit(Node.CustomTag n)
                                throws JasperException {
                            Node.Nodes b = n.getBody();
                            if (b != null && !b.isGeneratedInBuffer()) {
                                // Don't adjust lines for the nested tags that
                                // are also generated in buffers, because the
                                // adjustments will be done elsewhere.
                                b.visit(this);
                            }
                        }
                    });
                } catch (JasperException ex) {
                }
            }
        }
