    public void setWaiting(String description) {

        busyText = description;

        if (busyText == null) {

            // restore the cursors we saved
            if (fMain instanceof java.awt.Frame) {
                ((java.awt.Frame) fMain).setCursor(fMainCursor);
            } else {
                ((Component) fMain).setCursor(fMainCursor);
            }

            txtCommand.setCursor(txtCommandCursor);
            txtResult.setCursor(txtResultCursor);

            /** @todo: Enable actionButtons */
        } else {

            // save the old cursors
            if (fMainCursor == null) {
                fMainCursor = ((fMain instanceof java.awt.Frame)
                               ? (((java.awt.Frame) fMain).getCursor())
                               : (((Component) fMain).getCursor()));
                txtCommandCursor = txtCommand.getCursor();
                txtResultCursor  = txtResult.getCursor();
            }

            // set the cursors to the wait cursor
            if (fMain instanceof java.awt.Frame) {
                ((java.awt.Frame) fMain).setCursor(waitCursor);
            } else {
                ((Component) fMain).setCursor(waitCursor);
            }

            txtCommand.setCursor(waitCursor);
            txtResult.setCursor(waitCursor);

            /** @todo: Disable actionButtons */
        }

        setStatusLine(busyText, ((busyText == null) ? gResult.getRowCount()
                                                    : 0));
    }
