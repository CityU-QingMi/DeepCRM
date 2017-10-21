        private Node.JspBody findJspBody(Node parent) throws JasperException {
            Node.JspBody result = null;

            Node.Nodes subelements = parent.getBody();
            for (int i = 0; (subelements != null) && (i < subelements.size()); i++) {
                Node n = subelements.getNode(i);
                if (n instanceof Node.JspBody) {
                    result = (Node.JspBody) n;
                    break;
                }
            }

            return result;
        }
