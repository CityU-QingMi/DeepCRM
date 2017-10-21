    public static int getProperties(int sensitive, int updatable,
                                    int scrollable, int holdable,
                                    int returnable) {

        int combined = (sensitive << idx_sensitive)
                       | (updatable << idx_updatable)
                       | (scrollable << idx_scrollable)
                       | (holdable << idx_holdable)
                       | (returnable << idx_returnable);

        return combined;
    }
