    public void put(String prefix, Object value) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (c > SIZE)
                throw new IllegalArgumentException("'" + c + "' is too big.");
            if (current.next[c] == null)
                current.next[c] = new Node();
            current = current.next[c];
        }
        current.value = value;
    }
