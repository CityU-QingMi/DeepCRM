    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Key)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        Key other = (Key) o;
        return name.equals(other.name) && type.equals(other.type);
    }
