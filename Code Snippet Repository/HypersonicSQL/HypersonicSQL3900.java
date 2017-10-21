    public Grid() {

        super();

        fFont = new Font("Dialog", Font.PLAIN, 12);

        setLayout(null);

        sbHoriz = new Scrollbar(Scrollbar.HORIZONTAL);

        add(sbHoriz);

        sbVert = new Scrollbar(Scrollbar.VERTICAL);

        add(sbVert);
    }
