    void setStatusLine(String busyBaseString, int rowCount) {

        iReadyStatus.setSelected(busyBaseString != null);

        if (busyBaseString == null) {
            String additionalMsg = "";

            if (schemaFilter != null) {
                additionalMsg = " /  Tree showing objects in schema '"
                                + schemaFilter + "'";
            }

            if (rowCount > 1) {
                additionalMsg += " / " + rowCount + " rows retrieved";
            }

            jStatusLine.setText("  " + READY_STATUS + additionalMsg);
        } else {
            jStatusLine.setText("  " + busyBaseString + "...");
        }
    }
