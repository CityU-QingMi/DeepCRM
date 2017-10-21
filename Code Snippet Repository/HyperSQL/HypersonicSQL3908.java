    public boolean mouseExit(Event e, int x, int y) {

        if (bDrag) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            bDrag = false;
        }

        return true;
    }
