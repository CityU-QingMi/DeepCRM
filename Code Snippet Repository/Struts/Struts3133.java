    public TreeNode findChild(String name) {

        if (children == null)
            return (null);
        Iterator items = children.iterator();
        while (items.hasNext()) {
            TreeNode item = (TreeNode) items.next();
            if (name.equals(item.getName()))
                return (item);
        }
        return (null);

    }
