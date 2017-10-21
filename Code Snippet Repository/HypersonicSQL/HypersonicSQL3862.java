    void showHelp(String[] help) {

        txtCommand.setText(help[0]);
        txtResult.setText(help[1]);

        bHelp = true;

        pResult.removeAll();
        pResult.add("Center", txtResult);
        pResult.doLayout();
        txtCommand.requestFocus();
        txtCommand.setCaretPosition(help[0].length());
    }
