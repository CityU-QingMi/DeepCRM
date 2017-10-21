    void updateResult() {

        if (iResult == 0) {

            // in case 'help' has removed the grid
            if (bHelp) {
                pResult.removeAll();
                pResult.add("Center", gResult);
                pResult.doLayout();

                bHelp = false;
            }

            gResult.update();
            gResult.repaint();
        } else {
            showResultInText();
        }

        txtCommand.selectAll();
        txtCommand.requestFocus();
    }
