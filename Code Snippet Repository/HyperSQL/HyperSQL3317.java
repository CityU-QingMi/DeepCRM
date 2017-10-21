    public boolean handleEvent(Event e) {

        switch (e.id) {

            case Event.SCROLL_LINE_UP :
            case Event.SCROLL_LINE_DOWN :
            case Event.SCROLL_PAGE_UP :
            case Event.SCROLL_PAGE_DOWN :
            case Event.SCROLL_ABSOLUTE :
                iX = sbHoriz.getValue();
                iY = iRowHeight * sbVert.getValue();

                repaint();

                return true;
        }

        return super.handleEvent(e);
    }
