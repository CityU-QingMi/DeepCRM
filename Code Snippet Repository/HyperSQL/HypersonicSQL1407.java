    private NodeAVLDisk findNode(PersistentStore store, long pos) {

        NodeAVLDisk ret = null;
        RowAVLDisk  r   = (RowAVLDisk) store.get(pos, false);

        if (r != null) {
            ret = (NodeAVLDisk) r.getNode(iId);
        }

        return ret;
    }
