    public NodeList getChildNodes() {
        return new NodeList() {
            public Node item(int i) {
                return getRootElement();
            }

            public int getLength() {
                return 1;
            }
        };
    }
