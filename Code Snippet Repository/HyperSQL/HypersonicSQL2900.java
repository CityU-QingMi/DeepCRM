    private String roleMapToString(OrderedHashSet roles) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < roles.size(); i++) {
            if (sb.length() > 0) {
                sb.append(',');
            }

            Grantee role = (Grantee) roles.get(i);

            sb.append(role.getName().getStatementName());
        }

        return sb.toString();
    }
