    protected void toString(StringBuffer sb, int indent,
                            TreeNode node) {

        int indent2 = indent + 2;

        // Reconstruct an opening node
        for (int i = 0; i < indent; i++)
            sb.append(' ');
        sb.append('<');
        sb.append(node.getName());
        Iterator names = node.findAttributes();
        while (names.hasNext()) {
            sb.append(' ');
            String name = (String) names.next();
            sb.append(name);
            sb.append("=\"");
            String value = node.findAttribute(name);
            sb.append(value);
            sb.append("\"");
        }
        sb.append(">\n");

        // Reconstruct the body text of this node (if any)
        String body = node.getBody();
        if ((body != null) && (body.length() > 0)) {
            for (int i = 0; i < indent2; i++)
                sb.append(' ');
            sb.append(body);
            sb.append("\n");
        }

        // Reconstruct child nodes with extra indentation
        Iterator children = node.findChildren();
        while (children.hasNext()) {
            TreeNode child = (TreeNode) children.next();
            toString(sb, indent2, child);
        }

        // Reconstruct a closing node marker
        for (int i = 0; i < indent; i++)
            sb.append(' ');
        sb.append("</");
        sb.append(node.getName());
        sb.append(">\n");

    }
