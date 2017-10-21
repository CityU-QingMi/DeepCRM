    public boolean mouseMove(Event e, int x, int y) {

        if (y <= iRowHeight) {
            int xb = x;

            x += iX - iGridWidth;

            int i = iColCount - 1;

            for (; i >= 0; i--) {
                if (x > -7 && x < 7) {
                    break;
                }

                x += iColWidth[i];
            }

            if (i >= 0) {
                if (!bDrag) {
                    setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));

                    bDrag    = true;
                    iXDrag   = xb - iColWidth[i];
                    iColDrag = i;
                }

                return true;
            }
        }

        return mouseExit(e, x, y);
    }
