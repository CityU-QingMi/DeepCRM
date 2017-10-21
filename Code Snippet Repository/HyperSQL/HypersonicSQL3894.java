    private void clearResultPanel() {

        gResult.setHead(new Object[0]);
        gResult.clear();

        if (gridFormat) {
            gResult.fireTableChanged(null);
        } else {
            showResultInText();
        }
    }
