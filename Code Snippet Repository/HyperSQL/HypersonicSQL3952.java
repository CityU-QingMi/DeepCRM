    private void displayTable(TransferTable t) {

        tCurrent = t;

        if (t == null) {
            return;
        }

        tSourceTable.setText(t.Stmts.sSourceTable);
        tDestTable.setText(t.Stmts.sDestTable);
        tDestDrop.setText(t.Stmts.sDestDrop);
        tDestCreateIndex.setText(t.Stmts.sDestCreateIndex);
        tDestDropIndex.setText(t.Stmts.sDestDropIndex);
        tDestCreate.setText(t.Stmts.sDestCreate);
        tDestDelete.setText(t.Stmts.sDestDelete);
        tSourceSelect.setText(t.Stmts.sSourceSelect);
        tDestInsert.setText(t.Stmts.sDestInsert);
        tDestAlter.setText(t.Stmts.sDestAlter);
        cTransfer.setState(t.Stmts.bTransfer);
        cDrop.setState(t.Stmts.bDrop);
        cCreate.setState(t.Stmts.bCreate);
        cDropIndex.setState(t.Stmts.bDropIndex);
        cCreateIndex.setState(t.Stmts.bCreateIndex);
        cDelete.setState(t.Stmts.bDelete);
        cInsert.setState(t.Stmts.bInsert);
        cAlter.setState(t.Stmts.bAlter);
        cFKForced.setState(t.Stmts.bFKForced);
        cIdxForced.setState(t.Stmts.bIdxForced);
    }
