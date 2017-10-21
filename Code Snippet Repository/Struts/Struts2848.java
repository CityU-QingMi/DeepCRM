    public boolean isGreater(Mark other) {

        boolean greater = false;

        if (this.line > other.line) {
            greater = true;
        } else if (this.line == other.line && this.col > other.col) {
            greater = true;
        }

        return greater;
    }
