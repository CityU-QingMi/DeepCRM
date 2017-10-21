    private void setLF(String newLAF) {

        if (currentLAF != null && currentLAF == newLAF) {    // No change
            return;
        }

        if (pResult != null && gridFormat) {
            pResult.removeAll();
        }

        CommonSwing.setSwingLAF((Component) fMain, newLAF);

        if (pResult != null && gridFormat) {
            setResultsInGrid();
        }

        currentLAF = newLAF;

        if (currentLAF.equals(CommonSwing.Native)) {
            rbNativeLF.setSelected(true);
        } else if (currentLAF.equals(CommonSwing.Java)) {
            rbJavaLF.setSelected(true);
        } else if (currentLAF.equals(CommonSwing.Motif)) {
            rbMotifLF.setSelected(true);
        }
    }
