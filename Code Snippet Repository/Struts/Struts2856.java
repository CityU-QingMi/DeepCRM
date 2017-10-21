        public boolean hasEmptyBody() {
            boolean hasEmptyBody = true;
            Nodes nodes = getBody();
            if (nodes != null) {
                int numChildNodes = nodes.size();
                for (int i = 0; i < numChildNodes; i++) {
                    Node n = nodes.getNode(i);
                    if (!(n instanceof NamedAttribute)) {
                        if (n instanceof JspBody) {
                            hasEmptyBody = (n.getBody() == null);
                        } else {
                            hasEmptyBody = false;
                        }
                        break;
                    }
                }
            }

            return hasEmptyBody;
        }
