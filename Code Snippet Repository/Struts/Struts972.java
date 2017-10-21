    protected void populateParams() {
        super.populateParams();

        InputTransferSelect inputTransferSelect = (InputTransferSelect) component;
        inputTransferSelect.setSize(size);
        inputTransferSelect.setMultiple(multiple);
        inputTransferSelect.setAllowRemoveAll(allowRemoveAll);
        inputTransferSelect.setAllowUpDown(allowUpDown);
        inputTransferSelect.setLeftTitle(leftTitle);
        inputTransferSelect.setRightTitle(rightTitle);

        inputTransferSelect.setButtonCssClass(buttonCssClass);
        inputTransferSelect.setButtonCssStyle(buttonCssStyle);

        inputTransferSelect.setAddLabel(addLabel);
        inputTransferSelect.setRemoveLabel(removeLabel);
        inputTransferSelect.setRemoveAllLabel(removeAllLabel);
        inputTransferSelect.setUpLabel(upLabel);
        inputTransferSelect.setDownLabel(downLabel);
        inputTransferSelect.setHeaderKey(headerKey);
        inputTransferSelect.setHeaderValue(headerValue);
    }
