    protected Object string(char quote) {
        this.buf.setLength(0);

        while ((this.c != quote) && (this.c != CharacterIterator.DONE)) {
            if (this.c == '\\') {
                this.next();

                if (this.c == 'u') {
                    this.add(this.unicode());
                } else {
                    Object value = escapes.get(this.c);

                    if (value != null) {
                        this.add((Character) value);
                    }
                }
            } else {
                this.add();
            }
        }

        this.next();

        return this.buf.toString();
    }
