    public CharacterType(Collation collation, int type, long precision) {

        super(Types.SQL_VARCHAR, type, precision, 0);

        if (collation == null) {
            collation = Collation.getDefaultInstance();
        }

        this.collation = collation;
        this.charset   = Charset.getDefaultInstance();
        nameString     = getNameStringPrivate();
    }
