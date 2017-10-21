    private static String htmlRow(int colType) {
        switch (colType) {
            case COL_HEAD :
                return PRE_TR + "<TR>";

            case COL_ODD :
                return PRE_TR + "<TR class=\"sqltool-odd\">";

            case COL_EVEN :
                return PRE_TR + "<TR class=\"sqltool-even\">";
        }

        return null;
    }
