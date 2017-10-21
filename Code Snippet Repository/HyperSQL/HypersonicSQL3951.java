    public void itemStateChanged(ItemEvent e) {

        ItemSelectable item = e.getItemSelectable();

        if (item == lTable) {
            if (iSelectionStep == SELECT_SOURCE_TABLES) {
                String table    = lTable.getSelectedItem();
                int    selected = ((Integer) e.getItem()).intValue();

                for (int i = 0; i < tTable.size(); i++) {
                    TransferTable t = (TransferTable) tTable.elementAt(i);

                    if (t == null) {
                        continue;
                    }

                    if (i == selected) {
                        saveTable();
                        displayTable(t);
                        updateEnabled(true);
                    }
                }
            }
        } else {

            // it must be a checkbox
            saveTable();
            updateEnabled(true);
        }
    }
