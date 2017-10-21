    void destroy() {

        if (indexList.length == 0) {
            return;
        }

        IndexAVL idx  = (IndexAVL) indexList[0];
        NodeAVL  root = (NodeAVL) accessorList[0];

        idx.unlinkNodes(this, root);
    }
