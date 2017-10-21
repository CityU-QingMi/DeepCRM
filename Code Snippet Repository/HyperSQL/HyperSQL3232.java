    public boolean mouseDrag(Event e, int x, int y) {

        if (bDrag && x < iWidth) {
            int w = x - iXDrag;

            if (w < 0) {
                w = 0;
            }

            iColWidth[iColDrag] = w;

            adjustScroll();
            repaint();
        }

        return true;
    }
