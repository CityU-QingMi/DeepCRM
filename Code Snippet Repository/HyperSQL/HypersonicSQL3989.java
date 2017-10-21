    boolean contains(String pattern) {
        Vector lines = this.lines;
        int    size  = lines.size();

        for (int i = 0; i < size; i++) {
            if (((String)lines.elementAt(i)).indexOf(pattern) >= 0) {
                return true;
            }
        }

        return false;
    }
