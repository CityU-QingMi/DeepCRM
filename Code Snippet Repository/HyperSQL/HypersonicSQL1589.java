    public ResultSet getSuperTypes(
            String catalog, String schemaPattern,
            String typeNamePattern) throws SQLException {

        if (wantsIsNull(typeNamePattern)) {
            return executeSelect("SYSTEM_SUPERTYPES", "0=1");
        }
        catalog       = translateCatalog(catalog);
        schemaPattern = translateSchema(schemaPattern);

        StringBuffer select = toQueryPrefixNoSelect(
            "SELECT * FROM (SELECT USER_DEFINED_TYPE_CATALOG, USER_DEFINED_TYPE_SCHEMA, USER_DEFINED_TYPE_NAME,"
            + "CAST (NULL AS INFORMATION_SCHEMA.SQL_IDENTIFIER), CAST (NULL AS INFORMATION_SCHEMA.SQL_IDENTIFIER), DATA_TYPE "
            + "FROM INFORMATION_SCHEMA.USER_DEFINED_TYPES "
            + "UNION SELECT DOMAIN_CATALOG, DOMAIN_SCHEMA, DOMAIN_NAME,NULL,NULL, DATA_TYPE "
            + "FROM INFORMATION_SCHEMA.DOMAINS) "
            + "AS SUPERTYPES(TYPE_CAT, TYPE_SCHEM, TYPE_NAME, SUPERTYPE_CAT, SUPERTYPE_SCHEM, SUPERTYPE_NAME) ").append(
                and("TYPE_CAT", "=", catalog)).append(
                and("TYPE_SCHEM", "LIKE", schemaPattern)).append(
                and("TYPE_NAME", "LIKE", typeNamePattern));

        return execute(select.toString());
    }
