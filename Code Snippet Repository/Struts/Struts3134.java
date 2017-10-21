    public Iterator findChildren(String name) {

        if (children == null)
            return (Collections.EMPTY_LIST.iterator());

        ArrayList results = new ArrayList();
        Iterator items = children.iterator();
        while (items.hasNext()) {
            TreeNode item = (TreeNode) items.next();
            if (name.equals(item.getName()))
                results.add(item);
        }
        return (results.iterator());

    }
