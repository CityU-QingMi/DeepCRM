    private void ProcessNextStep() {

        switch (iSelectionStep) {

            case SELECT_SOURCE_CATALOG :
            case SELECT_DEST_CATALOG :
                if (CatalogToSelect()) {
                    fMain.show();

                    return;
                }
                break;

            case SELECT_DEST_SCHEMA :
            case SELECT_SOURCE_SCHEMA :
                if (SchemaToSelect()) {
                    fMain.show();

                    return;
                }
                break;

            case SELECT_SOURCE_TABLES :
                if (iTransferMode == TRFM_TRANSFER) {
                    bStart.setLabel("Start Transfer");
                } else if (iTransferMode == TRFM_DUMP) {
                    bStart.setLabel("Start Dump");
                } else if (iTransferMode == TRFM_RESTORE) {
                    bStart.setLabel("Start Restore");
                }

                bStart.invalidate();
                bStart.setEnabled(false);
                lTable.setMultipleMode(false);
                RefreshMainDisplay();
                break;

            default :
                break;
        }
    }
