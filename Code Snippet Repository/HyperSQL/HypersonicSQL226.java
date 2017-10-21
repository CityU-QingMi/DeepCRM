    public String describe(Session session, int blanks) {

        StringBuffer sb = new StringBuffer();

        sb.append('\n');

        for (int i = 0; i < blanks; i++) {
            sb.append(' ');
        }

        sb.append("FUNCTION ").append("=[\n");
        sb.append(name).append("(");

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            sb.append("[").append(nodes[i].describe(session,
                    blanks)).append("]");
        }

        sb.append(") returns ").append(dataType.getNameString());
        sb.append("]\n");

        return sb.toString();
    }
