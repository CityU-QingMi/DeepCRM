    public void windowClosing(WindowEvent ev) {

        stop();

        try {
            if (cConn != null) {
                cConn.close();
            }

            if (prefs != null) {
                prefs.autoRefresh   = autoRefresh;
                prefs.showRowCounts = displayRowCounts;
                prefs.showSysTables = showSys;
                prefs.showSchemas   = showSchemas;
                prefs.resultGrid    = gridFormat;
                prefs.showTooltips  = showTooltips;
                prefs.laf           = currentLAF;

                prefs.store();
            }
        } catch (Exception e) {

            //  Added: (weconsultants@users)
            CommonSwing.errorMessage(e);
        }

        if (fMain instanceof java.awt.Window) {
            ((java.awt.Window) fMain).dispose();
        }

        if (bMustExit) {
            System.exit(0);
        }
    }
