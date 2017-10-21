    private void calcAutoWidth(int i) {

        int w = 10;

        w = Math.max(w, fMetrics.stringWidth(sColHead[i]));

        for (int j = 0; j < iRowCount; j++) {
            String[] s = (String[]) (vData.elementAt(j));

            w = Math.max(w, fMetrics.stringWidth(s[i]));
        }

        iColWidth[i] = w + 6;
    }
