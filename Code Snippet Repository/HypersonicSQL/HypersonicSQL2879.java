    public static int getValueForJDBC(int type, int concurrency,
                                      int holdability) {

        int scrollable = type == ResultConstants.TYPE_FORWARD_ONLY ? 0
                                                                   : 1;
        int updatable  = concurrency == ResultConstants.CONCUR_UPDATABLE ? 1
                                                                         : 0;
        int holdable = holdability == ResultConstants.HOLD_CURSORS_OVER_COMMIT
                       ? 1
                       : 0;
        int prop = (updatable << idx_updatable)
                   | (scrollable << idx_scrollable)
                   | (holdable << idx_holdable);

        return prop;
    }
