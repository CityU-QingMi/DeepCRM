    public String toString() {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < aclEntries.size(); i++) {
            if (i > 0) {
                sb.append('\n');
            }

            sb.append("Entry " + (i + 1) + ": " + aclEntries.get(i));
        }

        return sb.toString();
    }
