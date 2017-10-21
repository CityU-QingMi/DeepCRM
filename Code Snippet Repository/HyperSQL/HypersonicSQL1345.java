    protected final void init(Session session) {

        // flag the Session-dependent cached tables
        Table t;

        for (int i = 0; i < sysTables.length; i++) {
            t = sysTables[i] = generateTable(session, null, i);

            if (t != null) {
                t.setDataReadOnly(true);
            }
        }

        GranteeManager gm    = database.getGranteeManager();
        Right          right = new Right();

        right.set(GrantConstants.SELECT, null);

        for (int i = 0; i < sysTableHsqlNames.length; i++) {
            if (sysTables[i] != null) {
                gm.grantSystemToPublic(sysTables[i], right);
            }
        }

        right = Right.fullRights;

        gm.grantSystemToPublic(Charset.SQL_CHARACTER, right);
        gm.grantSystemToPublic(Charset.SQL_IDENTIFIER_CHARSET, right);
        gm.grantSystemToPublic(Charset.SQL_TEXT, right);
        gm.grantSystemToPublic(TypeInvariants.SQL_IDENTIFIER, right);
        gm.grantSystemToPublic(TypeInvariants.YES_OR_NO, right);
        gm.grantSystemToPublic(TypeInvariants.TIME_STAMP, right);
        gm.grantSystemToPublic(TypeInvariants.CARDINAL_NUMBER, right);
        gm.grantSystemToPublic(TypeInvariants.CHARACTER_DATA, right);
    }
