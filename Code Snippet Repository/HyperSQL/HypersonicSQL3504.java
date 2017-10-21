    public void test() {

        try {
            checkSimpleViews();
            checkAsterisksCombined();
            checkMultipleTables();
            checkSubSelects();
            checkColumnLists();
            checkViewsOnViews();
            checkUnionViews();
        } catch (SQLException ex) {
            fail(ex.toString());
        }
    }
