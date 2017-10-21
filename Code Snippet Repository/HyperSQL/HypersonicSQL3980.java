    public void setBounds(int x, int y, int w, int h) {

        super.setBounds(x, y, w, h);

        iSbHeight = sbHoriz.getPreferredSize().height;
        iSbWidth  = sbVert.getPreferredSize().width;
        iHeight   = h - iSbHeight;
        iWidth    = w - iSbWidth;

        sbHoriz.setBounds(0, iHeight, iWidth, iSbHeight);
        sbVert.setBounds(iWidth, 0, iSbWidth, iHeight);
        adjustScroll();

        iImage = null;

        repaint();
    }
