    public String toString() {

        Iterator     it = iterator();
        StringBuffer sb = new StringBuffer();

        while (it.hasNext()) {
            if (sb.length() > 0) {
                sb.append(", ");
            } else {
                sb.append('[');
            }

            sb.append(it.next());
        }

        return sb.toString() + ']';
    }
