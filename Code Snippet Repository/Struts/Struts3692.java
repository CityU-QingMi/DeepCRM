    public int compareTo(Object o) {
        Link other = (Link) o;
        int result = from.compareTo(other.from);
        if (result != 0) {
            return result;
        }

        result = to.compareTo(other.to);
        if (result != 0) {
            return result;
        }

        result = label.compareTo(other.label);
        if (result != 0) {
            return result;
        }

        return new Integer(type).compareTo(other.type);
    }
