    public SchemaManager(Database database) {

        this.database         = database;
        defaultSchemaHsqlName = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
        catalogNameArray      = new HsqlName[]{ database.getCatalogName() };

        Schema schema =
            new Schema(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME,
                       SqlInvariants.INFORMATION_SCHEMA_HSQLNAME.owner);

        schemaMap.put(schema.getName().name, schema);

        try {
            schema.charsetLookup.add(Charset.SQL_TEXT, false);
            schema.charsetLookup.add(Charset.SQL_IDENTIFIER_CHARSET, false);
            schema.charsetLookup.add(Charset.SQL_CHARACTER, false);
            schema.collationLookup.add(Collation.getDefaultInstance(), false);
            schema.collationLookup.add(
                Collation.getDefaultIgnoreCaseInstance(), false);
            schema.typeLookup.add(TypeInvariants.CARDINAL_NUMBER, false);
            schema.typeLookup.add(TypeInvariants.YES_OR_NO, false);
            schema.typeLookup.add(TypeInvariants.CHARACTER_DATA, false);
            schema.typeLookup.add(TypeInvariants.SQL_IDENTIFIER, false);
            schema.typeLookup.add(TypeInvariants.TIME_STAMP, false);
            schema.typeLookup.add(TypeInvariants.NCNAME, false);
            schema.typeLookup.add(TypeInvariants.URI, false);
        } catch (HsqlException e) {}
    }
