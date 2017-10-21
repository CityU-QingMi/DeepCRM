    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        final Identity identity = (Identity) o;

        if ( !firstname.equals( identity.firstname ) ) return false;
        if ( !lastname.equals( identity.lastname ) ) return false;

        return true;
    }
