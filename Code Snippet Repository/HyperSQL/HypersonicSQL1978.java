    public synchronized String toString() {

        StringBuffer sb = new StringBuffer();

        sb.append(super.toString());
        sb.append(" : size=");
        sb.append(count);
        sb.append(' ');
        sb.append('[');

        for (int i = 0; i < count; i++) {
            sb.append(heap[i]);

            if (i + 1 < count) {
                sb.append(',');
                sb.append(' ');
            }
        }

        sb.append(']');

        return sb.toString();
    }
