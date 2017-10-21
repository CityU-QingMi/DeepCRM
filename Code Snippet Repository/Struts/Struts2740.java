        private void prepareParams(Node parent) throws JasperException {
            if (parent == null)
                return;

            Node.Nodes subelements = parent.getBody();
            if (subelements != null) {
                for (int i = 0; i < subelements.size(); i++) {
                    Node n = subelements.getNode(i);
                    if (n instanceof Node.ParamAction) {
                        Node.Nodes paramSubElements = n.getBody();
                        for (int j = 0; (paramSubElements != null)
                                && (j < paramSubElements.size()); j++) {
                            Node m = paramSubElements.getNode(j);
                            if (m instanceof Node.NamedAttribute) {
                                generateNamedAttributeValue((Node.NamedAttribute) m);
                            }
                        }
                    }
                }
            }
        }
