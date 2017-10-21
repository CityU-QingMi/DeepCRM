    public String getSQL() {

        StringBuffer sb   = new StringBuffer(64);
        String       left = getContextSQL(nodes[LEFT]);

        sb.append(left).append('[');
        sb.append(nodes[RIGHT].getSQL()).append(']');

        return sb.toString();
    }
