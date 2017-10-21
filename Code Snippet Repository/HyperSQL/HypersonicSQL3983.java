    void adjustScroll() {

        iTreeHeight = iRowHeight * (iRowCount + 1);

        // correct would be iMaxTextLength + iMaxIndent*iIndentWidth
        iTreeWidth = iMaxTextLength * 2;

        sbHoriz.setValues(iX, iWidth, 0, iTreeWidth);

        int v = iY / iRowHeight,
            h = iHeight / iRowHeight;

        sbVert.setValues(v, h, 0, iRowCount + 1);

        iX = sbHoriz.getValue();
        iY = iRowHeight * sbVert.getValue();
    }
