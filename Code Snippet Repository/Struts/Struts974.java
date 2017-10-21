    protected void populateParams() {
        super.populateParams();

        OptionTransferSelect optionTransferSelect = (OptionTransferSelect) component;
        optionTransferSelect.setAllowAddToLeft(allowAddToLeft);
        optionTransferSelect.setAllowAddToRight(allowAddToRight);
        optionTransferSelect.setAllowAddAllToLeft(allowAddAllToLeft);
        optionTransferSelect.setAllowAddAllToRight(allowAddAllToRight);
        optionTransferSelect.setAllowSelectAll(allowSelectAll);
        optionTransferSelect.setAllowUpDownOnLeft(allowUpDownOnLeft);
        optionTransferSelect.setAllowUpDownOnRight(allowUpDownOnRight);

        optionTransferSelect.setAddToLeftLabel(addToLeftLabel);
        optionTransferSelect.setAddToRightLabel(addToRightLabel);
        optionTransferSelect.setAddAllToLeftLabel(addAllToLeftLabel);
        optionTransferSelect.setAddAllToRightLabel(addAllToRightLabel);
        optionTransferSelect.setSelectAllLabel(selectAllLabel);
        optionTransferSelect.setLeftUpLabel(leftUpLabel);
        optionTransferSelect.setLeftDownLabel(leftDownLabel);
        optionTransferSelect.setRightUpLabel(rightUpLabel);
        optionTransferSelect.setRightDownLabel(rightDownLabel);

        optionTransferSelect.setButtonCssClass(buttonCssClass);
        optionTransferSelect.setButtonCssStyle(buttonCssStyle);

        optionTransferSelect.setLeftTitle(leftTitle);
        optionTransferSelect.setRightTitle(rightTitle);

        optionTransferSelect.setAddToLeftOnclick(addToLeftOnclick);
        optionTransferSelect.setAddToRightOnclick(addToRightOnclick);
        optionTransferSelect.setAddAllToLeftOnclick(addAllToLeftOnclick);
        optionTransferSelect.setAddAllToRightOnclick(addAllToRightOnclick);
        optionTransferSelect.setSelectAllOnclick(selectAllOnclick);
        optionTransferSelect.setUpDownOnLeftOnclick(upDownOnLeftOnclick);
        optionTransferSelect.setUpDownOnRightOnclick(upDownOnRightOnclick);
    }
