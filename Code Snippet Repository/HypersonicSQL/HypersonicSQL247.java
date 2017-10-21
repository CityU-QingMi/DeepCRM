    public boolean equals(Object other) {

        if (other instanceof HsqlException) {
            HsqlException o = (HsqlException) other;

            return code == o.code && equals(state, o.state)
                   && equals(message, o.message);
        }

        return false;
    }
