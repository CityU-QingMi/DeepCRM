    void adjustScroll() {

        if (iRowHeight == 0) {
            return;
        }

        int w = 0;

        for (int i = 0; i < iColCount; i++) {
            w += iColWidth[i];
        }

        iGridWidth  = w;
        iGridHeight = iRowHeight * (iRowCount + 1);

        sbHoriz.setValues(iX, iWidth, 0, iGridWidth);

        int v = iY / iRowHeight,
            h = iHeight / iRowHeight;

        sbVert.setValues(v, h, 0, iRowCount + 1);

        iX = sbHoriz.getValue();
        iY = iRowHeight * sbVert.getValue();
    }
