    private NodeAVLDiskLarge findNode(PersistentStore store, long pos) {

        NodeAVLDiskLarge ret = null;
        RowAVLDisk       r   = (RowAVLDisk) store.get(pos, false);

        if (r != null) {
            ret = (NodeAVLDiskLarge) r.getNode(iId);
        }

        return ret;
    }
