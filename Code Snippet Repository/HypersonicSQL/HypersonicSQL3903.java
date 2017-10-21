    public void setHead(String[] head) {

        iColCount = head.length;
        sColHead  = new String[iColCount];
        iColWidth = new int[iColCount];

        for (int i = 0; i < iColCount; i++) {
            sColHead[i]  = head[i];
            iColWidth[i] = 100;
        }

        iRowCount  = 0;
        iRowHeight = 0;
        vData      = new Vector();
    }
