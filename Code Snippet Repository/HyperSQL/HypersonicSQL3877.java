    public final void mouseReleased(final MouseEvent e) {

        if (alreadyHandled == e) {
            return;
        }

        handlePopup(e);

        alreadyHandled = e;
    }
