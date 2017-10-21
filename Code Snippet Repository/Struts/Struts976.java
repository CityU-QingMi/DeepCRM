    protected void populateParams() {
        super.populateParams();

        UpDownSelect c = (UpDownSelect) component;

        c.setAllowMoveUp(allowMoveUp);
        c.setAllowMoveDown(allowMoveDown);
        c.setAllowSelectAll(allowSelectAll);

        c.setMoveUpLabel(moveUpLabel);
        c.setMoveDownLabel(moveDownLabel);
        c.setSelectAllLabel(selectAllLabel);

    }
