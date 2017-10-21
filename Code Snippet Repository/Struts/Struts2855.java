        private int makeCustomNestingLevel() {
            int n = 0;
            Node p = parent;
            while (p != null) {
                if ((p instanceof Node.CustomTag)
                        && qName.equals(((Node.CustomTag) p).qName)) {
                    n++;
                }
                p = p.parent;
            }
            return n;
        }
