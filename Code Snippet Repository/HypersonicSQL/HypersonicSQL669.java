    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj instanceof Row) {
            return ((Row) obj).table == table
                   && ((Row) obj).position == position;
        }

        return false;
    }
