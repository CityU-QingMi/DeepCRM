    Document appendDocument(Document doc) {
        if (doc != null) {
            int    count = doc.size();
            Vector src   = doc.lines;
            Vector dst   = this.lines;

            for (int i = 0; i < count; i++) {
                dst.addElement(src.elementAt(i));
            }
        }

        return this;
    }
