    public final void mousePressed(final MouseEvent e) {

        if (alreadyHandled == e) {
            return;
        }

        handlePopup(e);

        alreadyHandled = e;
    }
