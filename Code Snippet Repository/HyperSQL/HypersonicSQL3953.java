    private void updateEnabled(boolean and) {

        boolean b = cTransfer.getState();

        tDestTable.setEnabled(and && b);
        tDestDrop.setEnabled(and && b && cDrop.getState());
        tDestCreate.setEnabled(and && b && cCreate.getState());
        tDestDelete.setEnabled(and && b && cDelete.getState());
        tDestCreateIndex.setEnabled(and && b && cCreateIndex.getState());
        tDestDropIndex.setEnabled(and && b && cDropIndex.getState());
        tSourceSelect.setEnabled(and && b);
        tDestInsert.setEnabled(and && b && cInsert.getState());
        tDestAlter.setEnabled(and && b && cAlter.getState());
        cDrop.setEnabled(and && b);
        cCreate.setEnabled(and && b);
        cDelete.setEnabled(and && b);
        cCreateIndex.setEnabled(and && b);
        cDropIndex.setEnabled(and && b);
        cInsert.setEnabled(and && b);
        cAlter.setEnabled(and && b);
        cFKForced.setEnabled(cAlter.getState());
        cIdxForced.setEnabled(cCreateIndex.getState());
        bStart.setEnabled(and);

        if (iTransferMode == TRFM_TRANSFER) {
            bContinue.setEnabled(and);
        }
    }
