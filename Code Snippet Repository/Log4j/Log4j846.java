    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route(");
        sb.append("type=");
        if (appenderRef != null) {
            sb.append("static Reference=").append(appenderRef);
        } else if (node != null) {
            sb.append("dynamic - type=").append(node.getName());
        } else {
            sb.append("invalid Route");
        }
        if (key != null) {
            sb.append(" key='").append(key).append('\'');
        } else {
            sb.append(" default");
        }
        sb.append(')');
        return sb.toString();
    }
