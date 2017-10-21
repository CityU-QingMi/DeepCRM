    private void LoadPrefs(String f) {

        TransferTable t;

        trace("Parsing Settings file");
        bStart.setEnabled(false);

        if (iTransferMode == TRFM_TRANSFER) {
            bContinue.setEnabled(false);
        }

        tTable = TransferCommon.loadPrefs(f, sourceDb, targetDb, this);
        iSelectionStep = SELECT_SOURCE_TABLES;

        lTable.removeAll();

        for (int i = 0; i < tTable.size(); i++) {
            t = (TransferTable) tTable.elementAt(i);

            lTable.add(t.Stmts.sSourceTable);
        }

        t = (TransferTable) tTable.elementAt(0);

        displayTable(t);
        lTable.select(0);
        updateEnabled(true);
        lTable.invalidate();

        if (iTransferMode == TRFM_TRANSFER) {
            bStart.setLabel("Start Transfer");
            trace("Edit definitions and press [Start Transfer]");
        } else if (iTransferMode == TRFM_DUMP) {
            bStart.setLabel("Start Dump");
            trace("Edit definitions and press [Start Dump]");
        } else if (iTransferMode == TRFM_RESTORE) {
            bStart.setLabel("Start Restore");
            trace("Edit definitions and press [Start Restore]");
        }

        bStart.invalidate();

        if (iTransferMode == TRFM_TRANSFER) {
            bContinue.setEnabled(false);
        }
    }
