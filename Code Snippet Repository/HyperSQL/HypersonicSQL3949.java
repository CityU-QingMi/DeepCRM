    private void RefreshMainDisplay() {

        lTable.removeAll();
        lTable.repaint();

        try {
            tTable = sourceDb.getTables(sSourceCatalog, sSourceSchemas);

            for (int i = 0; i < tTable.size(); i++) {
                TransferTable t = (TransferTable) tTable.elementAt(i);

                t.setDest(sDestSchema, targetDb);
                t.extractTableStructure(sourceDb, targetDb);
                lTable.add(t.Stmts.sSourceTable);
                lTable.select(i);
                displayTable(t);
            }

            bStart.setEnabled(true);

            if (iTransferMode == TRFM_TRANSFER) {
                trace("Edit definitions and press [Start Transfer]");
            } else if (iTransferMode == TRFM_DUMP) {
                trace("Edit definitions and press [Start Dump]");
            }
        } catch (Exception e) {
            trace("Exception reading source tables: " + e);
            e.printStackTrace();
        }

        fMain.show();
    }
