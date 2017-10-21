    public CachedObject getAccessor(Index key) {

        int     position = key.getPosition();
        NodeAVL node     = (NodeAVL) accessorList[position];

        if (node == null) {
            return null;
        }

        RowAVL row = (RowAVL) get(node.getRow(this), false);

        node                            = row.getNode(key.getPosition());
        accessorList[key.getPosition()] = node;

        return node;
    }
