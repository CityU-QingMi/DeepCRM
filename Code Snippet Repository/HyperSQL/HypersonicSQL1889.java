    public String toString() {

        StringBuffer sb = new StringBuffer(32 + elementCount * 3);

        sb.append("List : size=");
        sb.append(elementCount);
        sb.append(' ');
        sb.append('{');

        Iterator it = iterator();

        while (it.hasNext()) {
            sb.append(it.next());

            if (it.hasNext()) {
                sb.append(',');
                sb.append(' ');
            }
        }

        sb.append('}');

        return sb.toString();
    }
