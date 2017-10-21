    private String getSQLSimple() {

        StringBuffer sb = new StringBuffer(name).append('(');

        for (int i = 0; i < nodes.length; i++) {
            if (i > 0) {
                sb.append(',');
            }

            sb.append(nodes[i].getSQL());
        }

        sb.append(')');

        return sb.toString();
    }
