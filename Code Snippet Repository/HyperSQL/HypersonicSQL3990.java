    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Document) {
            Document other = (Document) o;

            Vector v1 = this.lines;
            Vector v2 = other.lines;

            if (v1.size() != v2.size()) {
                return false;
            }

            for (int i = v1.size() - 1; i >= 0; i--) {
                if (v1.elementAt(i).equals(v2.elementAt(i))) {
                    continue;
                } else {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
