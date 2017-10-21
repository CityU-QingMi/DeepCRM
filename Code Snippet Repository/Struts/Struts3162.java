    public void append(char c) {
        if (this.length + 1 > this.ch.length) {
            int newLength = this.ch.length * 2;
            if (newLength < this.ch.length + DEFAULT_SIZE)
                newLength = this.ch.length + DEFAULT_SIZE;
            char[] newch = new char[newLength];
            System.arraycopy(this.ch, 0, newch, 0, this.length);
            this.ch = newch;
        }
        this.ch[this.length] = c;
        this.length++;
    } // append(char)
