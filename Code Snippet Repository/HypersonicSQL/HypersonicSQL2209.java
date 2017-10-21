    public static int precision(BigDecimal o) {

        if (o == null) {
            return 0;
        }

//#ifdef JAVA6
        int precision;

        if (o.compareTo(BD_1) < 0 && o.compareTo(MBD_1) > 0) {
            precision = o.scale();
        } else {
            precision = o.precision();
        }

        return precision;

//#else
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/

//#endif JAVA6
    }
