        public String getSelectClauseNullString(int sqlType) {
                String literal;
                switch ( sqlType ) {
                        case Types.VARCHAR:
                        case Types.CHAR:
                                literal = "cast(null as varchar(100))";
                                break;
                        case Types.DATE:
                                literal = "cast(null as date)";
                                break;
                        case Types.TIMESTAMP:
                                literal = "cast(null as timestamp)";
                                break;
                        case Types.TIME:
                                literal = "cast(null as time)";
                                break;
                        default:
                                literal = "cast(null as int)";
                }
                return literal;
        }
